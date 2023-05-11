package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestBookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.GuestBook;

import java.time.LocalDateTime;

public interface GuestBookService {
    Long register(GuestBookDTO dto);

    PageResultDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    default GuestBook dtoToEntity(GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno() != null ? dto.getGno().longValue() : 0L) // use default value of 0L if dto.getGno() is null
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }


    default GuestBookDTO entityToDto(GuestBook entity){
        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
    GuestBookDTO read(Long gno);

    void remove(Long gno);
    void modify(GuestBookDTO dto);
}
