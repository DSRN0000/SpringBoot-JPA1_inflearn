package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){ //item은 jpa에 저장하기 전까지 id값이 없다.
        if (item.getId() == null){
            em.persist(item); //persist는 jpa가 제공. 신규로 등록하는 것.
        }else {
            em.merge(item); //merge는 업데이트 비슷한 것이다. null이 아니라면 업데이트 해준다.
         }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}

