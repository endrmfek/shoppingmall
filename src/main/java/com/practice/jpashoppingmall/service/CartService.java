package com.practice.jpashoppingmall.service;

import com.practice.jpashoppingmall.dto.CartItemDto;
import com.practice.jpashoppingmall.entity.Cart;
import com.practice.jpashoppingmall.entity.CartItem;
import com.practice.jpashoppingmall.entity.Item;
import com.practice.jpashoppingmall.entity.Member;
import com.practice.jpashoppingmall.repository.CartItemRepository;
import com.practice.jpashoppingmall.repository.CartRepository;
import com.practice.jpashoppingmall.repository.ItemRepository;
import com.practice.jpashoppingmall.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String email) {
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepository.findByMemberId(member.getId());

        //처음이라면 하나 만들어
        if(cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null) {
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }

    }
}
