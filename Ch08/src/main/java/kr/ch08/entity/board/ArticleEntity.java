package kr.ch08.entity.board;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="BoardArticle")
public class ArticleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    no;
	private String title;
	private String content;
	
	// 방향성을 고려해서 Article 에서 User 를 참조해야 Article 을 조회 할 때, File 이 동시에 참조 됨.
	// @JoinColumn(name="?") 선언으로 생성되는 칼럼명을 설정
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer")
	private UserEntity user;
	//private String writer;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "article")
	private FileEntity file;
	
	// 방향성을 고려해서 ARticle에서 comment 를 참조해야 Article 을 조회할떄 comment 가 동시에 참조됨
    // 양방향 관계에서 외래키를 갖는 엔티티의 속성을 mappedBy 속성으로 연결주체를 결정
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	private List<CommentEntity> comments; 
	
	@CreationTimestamp
	private LocalDateTime rdate;
}