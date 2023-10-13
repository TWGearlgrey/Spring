package kr.ch08.repository.board;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import kr.ch08.entity.board.ArticleEntity;
import kr.ch08.entity.board.CommentEntity;
import kr.ch08.entity.board.FileEntity;
import kr.ch08.entity.board.UserEntity;

@SpringBootTest
public class BoardRepositoryTests {

	@Autowired private ArticleRepository aRepo;
	@Autowired private CommentRepository cRepo;
	@Autowired private FileRepository    fRepo;
	@Autowired private UserRepository    uRepo;
	
	
	@DisplayName("insertUser TEST...")
	public void insertUser() {
		UserEntity user = UserEntity.builder()
							.uid("a103")
							.name("장보고")
							.hp("010-1234-1003")
							.build();
		uRepo.save(user);
	}
	
	
	@DisplayName("insertArticle TEST...")
	public void insertArticle() {
		
		UserEntity user = UserEntity.builder().uid("a103").build();
		
		ArticleEntity article = ArticleEntity.builder()
							.title("제목8 입니다.")
							.content("내용8 입니다.")
							.user(user)
							.build();
		aRepo.save(article);
	}
	
	
	@DisplayName("insertComment TEST...")
	public void insertComment() {

		UserEntity user = UserEntity.builder().uid("a102").build();
		
		ArticleEntity article = ArticleEntity.builder()
							.no(10)
							.build();
		
		CommentEntity comment = CommentEntity.builder()
							.content("댓글8 입니다.")
							.user(user)
							.article(article)
							.build();
		cRepo.save(comment);
	}
	
	
	@DisplayName("insertFile TEST...")
	public void insertFile() {
		
		ArticleEntity article = ArticleEntity.builder()
							.no(10)
							.build();
		
		FileEntity file = FileEntity.builder()
							.oName("test8.txt")
							.sName("zpiu-hjkl-bnmv.txt")
							.article(article)
							.build();
		fRepo.save(file);
	}
	
	
	@Transactional
	@DisplayName("selectArticles TEST...")
	public void selectArticles() {
		
		List<ArticleEntity> articles = aRepo.findAll();
		System.out.println(articles);
		
		for(ArticleEntity article : articles) {
			System.out.println(article);
		}
	}
	
	@Test
	@Transactional
	/*
	 * 양방향으로 처리되는 Entity 관계에서 다수의 SELECT 를 수행하기 때문에
	 *  단일 SELECT 처리 후 종료되는 특징으로 접속이 종료 됨 (no session)
	 *  따라서 그 다음 SELECT 실행에서 no session 에러 발생
	 *  이럴 경우 @Transactional 선언으로 트랜랜젝션 처리를 해줘야 함.
	 */
	@DisplayName("selectArticle TEST...")
	public void selectArticle() {
		
		// optional은 null 반환으로 인한 에러를 방지하기 위함.
		Optional<ArticleEntity> result = aRepo.findById(2);
		ArticleEntity article = result.orElseThrow();
		
		System.out.println(article);
	}
}