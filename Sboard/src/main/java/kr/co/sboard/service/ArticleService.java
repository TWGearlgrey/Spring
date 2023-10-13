package kr.co.sboard.service;

import kr.co.sboard.dto.ArticleDTO;
import kr.co.sboard.dto.FileDTO;
import kr.co.sboard.dto.PageRequestDTO;
import kr.co.sboard.dto.PageResponseDTO;
import kr.co.sboard.entity.ArticleEntity;
import kr.co.sboard.entity.FileEntity;
import kr.co.sboard.repository.ArticleRepository;
import kr.co.sboard.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    public PageResponseDTO findByParentAndCate(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        // Pageable pageable = PageRequest.of(pg-1, 10, Sort.Direction.DESC, "no");
        Page<ArticleEntity> result = articleRepository.findByParentAndCate(0, pageRequestDTO.getCate(), pageable);

        List<ArticleDTO> dtoList = result.getContent()
                                        .stream()
                                        .map(entity -> modelMapper.map(entity, ArticleDTO.class))
                                        .toList();

        int totalElement = (int) result.getTotalElements();


        return PageResponseDTO.builder()
                            .pageRequestDTO(pageRequestDTO)
                            .dtoList(dtoList)
                            .total(totalElement)
                            .build();
        /*
        List<ArticleDTO> list = articleRepository
                .findAll(pageable)
                .stream()
                .map(entity -> ArticleDTO.builder()
                        .no(entity.getNo())
                        .parent(entity.getParent())
                        .comment(entity.getComment())
                        .cate(entity.getCate())
                        .title(entity.getTitle())
                        .content(entity.getContent())
                        .hit(entity.getHit())
                        .writer(entity.getWriter())
                        .regip(entity.getRegip())
                        .rdate(entity.getRdate()).build())
                .collect(Collectors.toList());
        return list;
         */
    }

    public void save(ArticleDTO dto) {
        // 글 등록 ***File table의 ano는 Article table을 참조하기때문 글쓰기가 먼저.
        ArticleEntity savedEntity = articleRepository.save(dto.toEntity());

        // 파일 업로드
        FileDTO fileDTO = fileUpLoad(dto);

        if(fileDTO != null) {
            // 파일 등록
            fileDTO.setAno(savedEntity.getNo());
            FileEntity result = fileRepository.save(fileDTO.toEntity());

            if(result != null) {
                savedEntity.setFile(1);
                articleRepository.save(savedEntity);
            }
        }
    }

    @Value("${spring.servlet.multipart.location}")
    private String filePath;
    // 어노테이션 값을 filePath에 초기화 시킴

    public FileDTO fileUpLoad(ArticleDTO dto) {
        MultipartFile mf = dto.getFname();

        if(!mf.isEmpty()) {
            // 파일 첨부 시
            String path = new File(filePath).getAbsolutePath();

            String oName = mf.getOriginalFilename();
            String ext = oName.substring(oName.lastIndexOf("."));
            String sName = UUID.randomUUID().toString() + ext;

            log.info("testdebug path : " + path);
            log.info("testdebug oName : " + oName);
            log.info("testdebug ext : " + ext);
            log.info("testdebug sName : " + sName);

            try {
                mf.transferTo(new File(path, sName));
            } catch (IOException e) {
                log.error("fileUpload : " + e.getMessage());
            }

            return FileDTO.builder().ofile(oName).sfile(sName).build();

        }
        // 파일 미첨부 시
        return null;
    }
}