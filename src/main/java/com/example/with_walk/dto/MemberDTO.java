package com.example.with_walk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor // ✅ 전체 매개변수 생성자 추가 (Builder가 이것을 사용)
public class MemberDTO {
    private Integer mNum; // m_num
    private String mName; // m_name
    private String mNickname; // m_nickname
    private String mEmail; // m_email
    private String mId; // m_id
    private String mPassword; // m_password
    private String mProfileImage; // m_profile_image
    private String mRole; // m_role
    private String mCreate; // m_create
}
