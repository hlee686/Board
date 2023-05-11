package org.zerock.guestbook.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.GuestBook;
import org.zerock.guestbook.entity.QGuestBook;


@SpringBootTest
public class GuestBookRepositoryTests {
	
	@Autowired
	private GuestBookRepository guestBookRepository;

	@Test
	public void testQuery1(){
		Pageable pageable = PageRequest.of(0,10, Sort.by("gno").ascending());
		QGuestBook qGuestBook = QGuestBook.guestBook;
		String keyword = "1";

		BooleanBuilder builder = new BooleanBuilder();
		BooleanExpression exTitle = qGuestBook.title.contains(keyword);
		BooleanExpression exContent = qGuestBook.content.contains(keyword);
		BooleanExpression exAll = exTitle.or(exContent);

		builder.and(exAll);
		builder.and(qGuestBook.gno.gt(100L));
		Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);
	}
	
	@Test
	public void insertDummies() {
		IntStream.rangeClosed(0, 29).forEach(i->{
			GuestBook guestbook = GuestBook.builder()
					.title("Title..." + i)
					.content("Content..."+i)
					.writer("Writer..."+i%10)
					.build();
			guestBookRepository.save(guestbook);
		});
	}

	@Test
	public void updateTest(){
		Optional<GuestBook> result = guestBookRepository.findById(160L);
		if(result.isPresent()){
			GuestBook guestbook = result.get();
			guestbook.changeContent("Michael Corleone");
			guestbook.changeTitle("The Godfather");

			guestBookRepository.save(guestbook);
		}
	}
}
