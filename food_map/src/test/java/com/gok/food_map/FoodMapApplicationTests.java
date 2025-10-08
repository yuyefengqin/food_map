package com.gok.food_map;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.order.service.ProductOrderService;
import com.gok.food_map.order.vo.UserOrderInfoVO;
import com.gok.food_map.product.entity.ProductSku;
import com.gok.food_map.product.service.ProductSkuServiceImpl;
import com.gok.food_map.product.vo.ProductSkuGetListVO;
import com.gok.food_map.shoppingCart.mapper.ShoppingCartMapper;
import com.gok.food_map.shoppingCart.service.ShoppingCartServiceImpl;
import com.gok.food_map.shoppingCart.vo.ShoppingCartGetVO;
import com.gok.food_map.util.TokenUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class FoodMapApplicationTests {
    @Resource
    private ProductSkuServiceImpl productSkuService;
    @Test
    void contextLoads() {
        ProductSku sku = new ProductSku();
        sku.setSkuId(100000000000000L);
        HashMap<String, String> specsPrice = new HashMap<>();
        specsPrice.put("abc","1");
        specsPrice.put("cba","2");
        specsPrice.put("bac","3");
        HashMap<String, String> elementSpecs = new HashMap<>();
        elementSpecs.put("包装","abc");
        elementSpecs.put("口味","cba");
        elementSpecs.put("材质","bac");
        sku.setSpuId(1973389343000000000L);
        sku.setSpecsPrice(specsPrice);
        sku.setElementSpecs(elementSpecs);
        sku.setCreateTime(LocalDateTime.now());
        sku.setStock(132);
//        productSkuService.save(sku);
        LambdaQueryWrapper<ProductSku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductSku::getSkuId, sku.getSkuId());
        System.out.println(productSkuService.getById(1973389343000000000L));
        System.out.println();
    }

    @Test
    void testPrintSku(){
        IPage<ProductSkuGetListVO> sku = productSkuService.getProductSkuBySpuId("1973389343917604865");
        sku.getRecords().forEach(System.out::println);
    }
    @Resource
    private ShoppingCartServiceImpl shoppingCartService;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Test
    void testShoppingCard(){
        List<ShoppingCartGetVO> carts = shoppingCartMapper.selectByUserId("1974135586684014593");
        carts.forEach(System.out::println);
    }
    @Test
    void testID(){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        System.out.println(snowflake.nextId());
    }
    @Resource
    private ProductOrderService productOrderService;
    @Test
    void testProductSku(){
//        List<UserOrderInfoVO> userOrderInfos = productOrderService.getUserOrderInfos(1973790950878760962L);
//        userOrderInfos.forEach(System.out::println);
//        System.out.println();
    }
}
