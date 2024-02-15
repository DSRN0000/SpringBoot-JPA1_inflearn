package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    /**
     * 상품 주문 폼
     */
    @GetMapping("/order")
    public String createForm(Model model){

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    /**
     * 상품 주문
     */

    @PostMapping("/order") // @RequestParam은 FormSummit 방식이라 하는데, html에서 select name에서 선택된 Id에 value가 넘어온다.
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){
        orderService.order(memberId, itemId, count); //상품 하나씩만 선택하도록 해뒀다. 멀티 하려면 이쪽 부분 고치기
        return "redirect:/orders";
    }

    /**
     * 주문 목록 검색
     */

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model){

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        System.out.println(orders.size());

        return "order/orderList";
    }

    /**
     * 주문 취소
     */

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId")Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:orders";
    }

}
