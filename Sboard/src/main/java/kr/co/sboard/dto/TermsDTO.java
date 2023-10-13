package kr.co.sboard.dto;

import kr.co.sboard.entity.TermsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TermsDTO {
    private int    no;
    private String terms;
    private String privacy;

    public TermsEntity toEntity() {
        return TermsEntity.builder()
                .no(no)
                .terms(terms)
                .privacy(privacy)
                .build();
    }
}