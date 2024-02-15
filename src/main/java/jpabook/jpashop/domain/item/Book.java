package jpabook.jpashop.domain.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") // 기본으로 두면 BOOK으로 들어간다.
@Getter
@Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
