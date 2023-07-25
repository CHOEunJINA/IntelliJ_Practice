package com.example.firstproject.entity;

import com.example.firstproject.DTO.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 Entity 여러개가 하나의 Article에 연관됨
    @JoinColumn(name="article_id") // "article_id" 컬럼에 Article의 대표값을 저장!
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 발생
        if(dto.getId()!=null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );

        //
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id!= dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");

        // 객체 갱신 -> dto의 닉네임이 null이 아니면 dto의 닉네임으로 갱신시킨다
        if(dto.getNickname()!=null)
            this.nickname = dto.getNickname();

        if(dto.getBody()!=null)
            this.body = dto.getBody();
    }
}
