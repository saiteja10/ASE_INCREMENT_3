package com.ase.api.service;

import com.ase.bean.BaseBean;
import com.ase.bean.RegisterBean;
import com.ase.bean.SaleOrderBean;
import com.ase.bean.list.ItemBeanList;
import com.ase.bean.list.OrderBeanList;
import com.ase.bean.list.SaleOrderBeanList;
import org.apache.cxf.jaxrs.model.wadl.ElementClass;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by sai on 4/5/2015.
 */
@Service
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface OrderService {
    @GET
    @Path("/{saleOrderId}")
    @ElementClass(response = ItemBeanList.class)
    public ItemBeanList getOrder(@PathParam("saleOrderId") Long saleOrderId) throws Exception;

    @PUT
    @Path("/place")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @ElementClass(request = OrderBeanList.class, response = BaseBean.class)
    public BaseBean placeOrder(@QueryParam("") OrderBeanList orderBeanList) throws Exception;

    @GET
    @Path("/open")
    @ElementClass(response = SaleOrderBeanList.class)
    public SaleOrderBeanList getOpenedOrders();

    @POST
    @Path("/accept/{saleOrderId}/{employeeId}")
    @ElementClass(response = BaseBean.class)
    public BaseBean acceptOrder(@PathParam("saleOrderId") Long saleOrderId, @PathParam("employeeId") Long employeeId) throws Exception;
}
