package com.myspring.xixi.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.myspring.xixi.common.dto.ChangePassDTO;
import com.myspring.xixi.common.dto.LoginDTO;
import com.myspring.xixi.common.dto.ShopDTD;
import com.myspring.xixi.common.lang.Result;
import com.myspring.xixi.domain.Business;
import com.myspring.xixi.domain.Goods;
import com.myspring.xixi.domain.Integral;
import com.myspring.xixi.domain.Integrals;
import com.myspring.xixi.mapper.IntegralsMapper;
import com.myspring.xixi.service.BusinessService;
import com.myspring.xixi.service.GoodsService;
import com.myspring.xixi.service.IntegralService;
import com.myspring.xixi.service.IntegralsService;
import com.myspring.xixi.service.impl.IntegralsServiceImpl;
import javafx.scene.canvas.GraphicsContext;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    BusinessService businessService;
    @Autowired
    IntegralService integralService;
    @Autowired
    IntegralsService integralsService;

    /**
     * 商城首页按分类展示所有商品
     * @return
     */

    @GetMapping("/transaction/commodity/classifyId")
    public Result getShops(Long classifyId) {
        //belongId   分类 0，教材教辅，1，生活用品,2,电子数码,3,其他

        List<Goods> myGoods = goodsService.getMyGoods(classifyId);
        List<ShopDTD> shopDTDS = new ArrayList<>();
        for (int i = 0; i < myGoods.size(); i++) {
            ShopDTD shopDTD = new ShopDTD();
            if(myGoods.get(i).getPass()==1){
            //分类
            shopDTD.setClassifyId(myGoods.get(i).getBelongId());
            //照片
            shopDTD.setCommodityImage(myGoods.get(i).getPicGoods());
            //名称
            shopDTD.setCommodityName(myGoods.get(i).getGoodsName());
            //Id
            shopDTD.setCommodityId(myGoods.get(i).getId());
            //价格
            shopDTD.setCommodityPrice(myGoods.get(i).getPrice());
            //联系方式
            shopDTD.setContactWay(myGoods.get(i).getDiscount());
            //审核状态
            shopDTD.setPass(myGoods.get(i).getPass());
            shopDTDS.add(shopDTD);
            }
        }
        //System.out.println(shopDTDS);
        return Result.success(shopDTDS);
    }
    /**
     *获取用户上传
     * @return
     * */
    @GetMapping("/transaction/user/getUpload")
    public Result getUpload(Integer uuid){
        List<Goods> allGoods = goodsService.getAllGoods();
        List<ShopDTD> shopDTDS =new ArrayList<>();
        for (int i = 0; i < allGoods.size(); i++) {
            ShopDTD shopDTD = new ShopDTD();
            if(allGoods.get(i).getPiece()==uuid){
                //分类
                shopDTD.setClassifyId(allGoods.get(i).getBelongId());
                //照片
                shopDTD.setCommodityImage(allGoods.get(i).getPicGoods());
                //名称
                shopDTD.setCommodityName(allGoods.get(i).getGoodsName());
                //Id
                shopDTD.setCommodityId(allGoods.get(i).getId());
                //价格
                shopDTD.setCommodityPrice(allGoods.get(i).getPrice());
                //联系方式
                shopDTD.setContactWay(allGoods.get(i).getDiscount());
                //审核状态
                shopDTD.setPass(allGoods.get(i).getPass());
                shopDTDS.add(shopDTD);
            }
        }
        return Result.success(shopDTDS);
    }
    /**
     * 获取未审核商品
     * */
    @GetMapping("/transaction/commodity/getAll")
    public Result getAll(){
        List<ShopDTD> shopDTDS =new ArrayList<>();
        List<Goods> allGoods = goodsService.getAllGoods();
        for (int i = 0; i < allGoods.size(); i++) {
            ShopDTD shopDTD = new ShopDTD();
            if(allGoods.get(i).getPass()==0){
                //分类名称
                if(allGoods.get(i).getBelongId()==0)
                    shopDTD.setClassName("教材教辅");
                else if (allGoods.get(i).getPass()==1) {
                    shopDTD.setClassName("生活用品");
                } else if (allGoods.get(i).getPass()==2) {
                    shopDTD.setClassName("电子数码");
                }else
                    shopDTD.setClassName("其它");
                //分类id
                shopDTD.setClassifyId(allGoods.get(i).getBelongId());
                //照片
                shopDTD.setCommodityImage(allGoods.get(i).getPicGoods());
                //名称
                shopDTD.setCommodityName(allGoods.get(i).getGoodsName());
                //Id
                shopDTD.setCommodityId(allGoods.get(i).getId());
                //价格
                shopDTD.setCommodityPrice(allGoods.get(i).getPrice());
                //联系方式
                shopDTD.setContactWay(allGoods.get(i).getDiscount());
                //审核状态
                shopDTD.setPass(allGoods.get(i).getPass());
                shopDTDS.add(shopDTD);
            }
        }
        return Result.success(shopDTDS);
    }

    /**
     * 用户反馈
     * @return
     * */
    @PostMapping("/transaction/user/feedback")
    public Result feedback (String information,String username){
        Integrals integrals =new Integrals();
        integrals.setUsername(username);
        integrals.setInformation(information);
        integrals.setPass(0L);
        boolean result =integralsService.save(integrals);
        if (result)return Result.success(200,"成功");
        else return Result.fail("上传失败");
    }
    /**
     * 获取用户反馈
     *
     * */
    @GetMapping("/transaction/getUser/feedback")
    public Result GetFeedback(){
        return Result.success(integralsService.getUpIntegral());
        //return Result.success(integralService.getUpIntegral());
    }
    /**
     * 用户上传
     *
     * */
    @PostMapping("/transaction/user/upload")
    public Result upLoad (  Integer uuid , Integer classifyId , String commodityName, Integer commodityPrice, Integer contactWay,MultipartFile commodityImg) throws IOException {
        Goods goods =new Goods();
        //名称
        goods.setGoodsName(commodityName);
        //分类
        goods.setBelongId(classifyId);
        //时间
        goods.setCreated(LocalDateTime.now());
        //照片
        goods.setPicGoods(commodityImg.getBytes());
        //价格
        goods.setPrice(commodityPrice);
        //联系方式
        goods.setDiscount(contactWay);
        //所属人id
        goods.setPiece(uuid);
        //审核状态
        goods.setPass(0);
        boolean result =goodsService.save(goods);
        if (result)return Result.success(200,"成功");
        else return Result.fail("上传失败");
    }
    /**
     * 管理员修改商品状态
     * @return
     */
    @PostMapping("/transaction/admin/check")
    public Result gPass(Integer commodityId,Integer newStatus){
        UpdateWrapper<Goods> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",commodityId);
        updateWrapper.set("pass",newStatus);
        boolean result = goodsService.update(updateWrapper);
        if(result) return Result.success("修改成功");
        else return Result.fail("修改失败");
    }
    /**
     * 已阅信息
     * */
    @GetMapping("/transaction/read")
    public Result Read(Integer id ,Integer pass){
        UpdateWrapper<Integrals> updateWrapper =new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("pass",pass);
        boolean result =integralsService.update(updateWrapper);
        if(result)
        //boolean result = integralsService.update(updateWrapper);
        return Result.success("阅读成功");
        else return Result.fail("失败");
    }
//        List<Goods> myGoods = goodsService.getMyGoods(classifyId);
//        if(myGoods.size() != 0){
//            List<Map<String, Object>> myGoodsMap = new ArrayList<>();
//            myGoods.forEach(goods -> {
//                Map<String, Object> map = BeanUtil.beanToMap(goods);
//                map.put("created", goods.getCreated().toLocalDate().toString());
//                map.put("picGoods", "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(goods.getPicGoods()));
//                myGoodsMap.add(map);
//            });
//            List<List<Map<String, Object>>> list = new ArrayList<>();
//            String time = (String) myGoodsMap.get(0).get("created");
//            int size = 0;
//            int index = 0;
//            for (int i = 0; i < myGoodsMap.size(); i++) {
//                String created = (String) myGoodsMap.get(i).get("created");
//                if(created.equals(time)){
//                    size++;
//                } else {
//                    time = created;
//                    List<Map<String, Object>> goods = myGoodsMap.subList(index, index + size);
//                    list.add(goods);
//                    index += size;
//                    size = 1;
//                }
//            }
//            list.add(myGoodsMap.subList(index, index + size));
//            List<Map<String, Object>> listMap = new ArrayList<>();
//            list.forEach(val -> {
//                Map<String, Object> map = new HashMap<>();
//                map.put("created", val.get(0).get("created"));
//                map.put("goods", val);
//                listMap.add(map);
//            });
//            return Result.success(listMap);
//        }else{
//            return Result.success(myGoods);
//        }
//    @GetMapping("/goods")
//    public Result getProducts(@RequestParam(defaultValue = "1") Integer currentPage){
//        Page page = new Page(currentPage, 12);
//        IPage pageData = goodsService.page(page, new QueryWrapper<Goods>().eq("pass", 1).orderByDesc("created"));
//        return Result.success(pageData);
//    }
//
//    @GetMapping("/goodsByName")
//    public Result goodsByName(@RequestParam(defaultValue = "1") Integer currentPage, String search){
//        Page page = new Page(currentPage, 12);
//        IPage pageData = goodsService.page(page,
//                new QueryWrapper<Goods>().eq("pass", 1)
//                        .like("goods_name", search)
//                        .orderByDesc("created"));
//        return Result.success(pageData);
//    }
//
//    @GetMapping("/goodsByShop")
//    public Result goodsByShop(@RequestParam(defaultValue = "1") Integer currentPage, String search){
//        Business shop = businessService.getOne(new QueryWrapper<Business>().like("shop_name", search));
//        Long shopId;
//        if(shop != null){
//            shopId = shop.getId();
//        }else{
//            shopId = -1L;
//        }
//        Page page = new Page(currentPage, 12);
//        IPage pageData = goodsService.page(page,
//                new QueryWrapper<Goods>().eq("pass", 1)
//                        .eq("belong_id", shopId)
//                        .orderByDesc("created"));
//        return Result.success(pageData);
//    }

//    /**
//     * 商家查看自己店铺中的商品
//     * @param shopId
//     * @return
//     */
//    @GetMapping("/goods/{shopId}")
//    public Result getMyProducts(@PathVariable("shopId") Integer shopId){
//        List<Goods> myGoods = goodsService.getMyGoods(shopId.longValue());
//        if(myGoods.size() != 0){
//            List<Map<String, Object>> myGoodsMap = new ArrayList<>();
//            myGoods.forEach(goods -> {
//                Map<String, Object> map = BeanUtil.beanToMap(goods);
//                map.put("created", goods.getCreated().toLocalDate().toString());
//                map.put("picGoods", "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(goods.getPicGoods()));
//                myGoodsMap.add(map);
//            });
//            List<List<Map<String, Object>>> list = new ArrayList<>();
//            String time = (String) myGoodsMap.get(0).get("created");
//            int size = 0;
//            int index = 0;
//            for (int i = 0; i < myGoodsMap.size(); i++) {
//                String created = (String) myGoodsMap.get(i).get("created");
//                if(created.equals(time)){
//                    size++;
//                } else {
//                    time = created;
//                    List<Map<String, Object>> goods = myGoodsMap.subList(index, index + size);
//                    list.add(goods);
//                    index += size;
//                    size = 1;
//                }
//            }
//            list.add(myGoodsMap.subList(index, index + size));
//            List<Map<String, Object>> listMap = new ArrayList<>();
//            list.forEach(val -> {
//                Map<String, Object> map = new HashMap<>();
//                map.put("created", val.get(0).get("created"));
//                map.put("goods", val);
//                listMap.add(map);
//            });
//            return Result.success(listMap);
//        }else{
//            return Result.success(myGoods);
//        }
//
//    }

//    /**
//     * 商家添加商品
//     * @return
//     * @throws IOException
//     */
//    @PostMapping("/goods/{shopId}")
//    public Result addGoods(@PathVariable("shopId") Integer shopId, Goods goods, MultipartFile goodsPic) throws IOException {
//        goods.setBelongId((int)shopId.longValue());
//        goods.setPicGoods(goodsPic.getBytes());
//        goods.setCreated(LocalDateTime.now());
//        boolean result = goodsService.save(goods);
//        return Result.success(null);
//    }
//    /**
//    *
//    *
//    * */
//    @PostMapping("/transaction/user/upload")
//    public Result upLoad (Integer contactWay){
//        Goods goods =new Goods();
//        goods.setCreated(LocalDateTime.now());
//        goods.setDiscount(contactWay);
//        boolean result =goodsService.save(goods);
//        if (result)return Result.success(200,"成功");
//        else return Result.fail("上传失败");
//    }
//    /**
//     * 管理员查看所有商品
//     * @param loginDTO
//     * @return
//     */
//    @RequiresAuthentication
//    @PostMapping("/goods")
//    public Result allGoods(@RequestBody LoginDTO loginDTO){
//        if(loginDTO.getLevel() == 1){
//            List<Goods> allGoods = goodsService.getAllGoods();
//            List<Map<String, Object>> goodsInfo = new ArrayList<>();
//            allGoods.forEach(goods -> {
//                Map<String, Object> map = BeanUtil.beanToMap(goods);
//                String shopName = businessService.getShopName((long) goods.getBelongId());
//                map.remove("belongId");
//                map.put("shopName", shopName);
//                map.put("created", goods.getCreated().toLocalDate());
//                map.put("picGoods", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(goods.getPicGoods()));
//                goodsInfo.add(map);
//            });
//            return Result.success(goodsInfo);
//        } else {
//            return Result.fail("无权查看信息！！！");
//        }
//    }

//    @GetMapping("/good/{goodId}")
//    public Result getGoodById(@PathVariable("goodId") Integer goodId){
//        return Result.success(goodsService.getById(goodId));
//    }
//
//    @PostMapping("/changeGood")
//    public Result changeGood(@RequestBody Goods goods){
//        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("id", goods.getId());
//        updateWrapper.set("piece", goods.getPiece()).set("pass", goods.getPass());
//        goodsService.update(updateWrapper);
//        return Result.success(null);
//    }
}
