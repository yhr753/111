package servlet;

import Dto.ItemsDto;
import com.google.gson.Gson;
import mapper.ItemsMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pojo.Items;
import util.Dbutil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(value = "/ItemServlet")
public class ItemServlet extends BaseServlet {


    // 处理多条件组合查询的方法
    public void listByDto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ItemsDto dto = new ItemsDto();

        try {
            int pageNo = 1;
            int pageSize = 3;
            if(req.getParameter("pageNo")!=null && !"".equals(req.getParameter("pageNo"))||req.getParameter("pageSize")!=null && !"".equals(req.getParameter("pageSize"))) {
                pageNo = Integer.parseInt(req.getParameter("pageNo"));
                pageSize = Integer.parseInt(req.getParameter("pageSize"));
            }

            String name = req.getParameter("name");
            dto.setName(name);
            dto.setPageSize(pageSize);
            dto.setPageNo((pageNo-1)*pageSize);


            // 根据dto查询数据
            SqlSessionFactory factory = Dbutil.getFactory();
            SqlSession session = factory.openSession();
            // 获取EmployeeMapper接口的实现类对象
            ItemsMapper mapper = session.getMapper(ItemsMapper.class);

            // 查询数据
            List<Items> itemsList = mapper.selectByDto(dto);
            // 统计数据
            dto.setPageNo(pageNo);
            Long total = mapper.countByDto(dto);
            dto.setTotal(total);
            session.close();

            req.setAttribute("itemsList", itemsList);
            req.setAttribute("total", total);
            req.setAttribute("dto",dto);
            req.getRequestDispatcher("/listbydto.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // 处理批量删除请求的方法
    public void deleteByBatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取页面提交的名为emp_ids的请求参数
        String[] emp_ids = req.getParameterValues("id");
        System.out.println(emp_ids);
        // string[] ---> int[]
       int[] ids = new int[emp_ids.length];
        for(int i=0;i<ids.length;i++) {
            ids[i] = Integer.parseInt(emp_ids[i]);
        }


        // 调用mapper方法进行处理
        SqlSessionFactory factory = Dbutil.getFactory();
        SqlSession session = factory.openSession(true);
        // 获取EmployeeMapper接口的实现类对象
        ItemsMapper mapper = session.getMapper(ItemsMapper.class);
        int result = mapper.deleteByIds(ids);

        session.close();
        resp.sendRedirect("ItemServlet?param=listByDto");

    }


    public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlSessionFactory factory = Dbutil.getFactory();
        SqlSession session = factory.openSession();
        // 获取EmployeeMapper接口的实现类对象
        ItemsMapper mapper = session.getMapper(ItemsMapper.class);
        List<Items> itemsList = mapper.queryAll();
        session.close();
        Gson gson = new Gson();
        String json = gson.toJson(itemsList);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Items item = new Items();
            BeanUtils.populate(item,request.getParameterMap());
            String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            item.setCreatetime(data);
            SqlSessionFactory factory = Dbutil.getFactory();
            SqlSession session = factory.openSession(true);
            // 获取EmployeeMapper接口的实现类对象
            ItemsMapper mapper = session.getMapper(ItemsMapper.class);
            int i = mapper.add(item);
            if(i>0){
                request.getRequestDispatcher("/ItemServlet?param=listByDto").forward(request,response);
            }else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SqlSessionFactory factory = Dbutil.getFactory();
        SqlSession session = factory.openSession();
        // 获取EmployeeMapper接口的实现类对象
        ItemsMapper mapper = session.getMapper(ItemsMapper.class);
        Items items = mapper.queryOne(id);
        request.setAttribute("item",items);
        request.getRequestDispatcher("edit.jsp").forward(request,response);
    }

    public void editItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Items item = new Items();
            BeanUtils.populate(item,request.getParameterMap());
            SqlSessionFactory factory = Dbutil.getFactory();
            SqlSession session = factory.openSession(true);
            // 获取EmployeeMapper接口的实现类对象
            ItemsMapper mapper = session.getMapper(ItemsMapper.class);
            int i = mapper.pdt(item);
            if(i>0){
                response.sendRedirect("list.jsp");
            }else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SqlSessionFactory factory = Dbutil.getFactory();
        SqlSession session = factory.openSession(true);
        // 获取EmployeeMapper接口的实现类对象
        ItemsMapper mapper = session.getMapper(ItemsMapper.class);
        int i = mapper.del(id);
        if(i>0){
            page(request,response);
        }else {
            response.sendRedirect("index.jsp");
        }
    }
}
