package net.nigne.yourtour.book.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.nigne.yourtour.common.domain.Name;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Auther auther;

    @Embedded
    private Category category;

    @Embedded
    private Publisher publisher;

    private Long price;

    @Builder(builderMethodName = "createBuilder")
    public Book(String name, String auther, String category, String publisher, Long price) {
        this.name = new Name(name);
        this.auther = new Auther(auther);
        this.category = new Category(category);
        this.publisher = new Publisher(publisher);
        this.price = price;
    }

    public String getName() {
        if(ObjectUtils.isEmpty(name)) {
            return "";
        }
        return name.getName();
    }

    public String getAuther() {
        if(ObjectUtils.isEmpty(auther)) {
            return "";
        }
        return auther.getAuther();
    }

    public String getCategory() {
        if(ObjectUtils.isEmpty(category)) {
            return "";
        }
        return category.getCategory();
    }

    public String getPublisher() {
        if(ObjectUtils.isEmpty(publisher)) {
            return "";
        }
        return publisher.getPublisher();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name=" + name +
                ", auther=" + auther +
                ", category=" + category +
                ", publisher=" + publisher +
                ", price=" + price +
                '}';
    }
}
