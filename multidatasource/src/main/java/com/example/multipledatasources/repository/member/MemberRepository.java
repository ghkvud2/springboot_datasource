package com.example.multipledatasources.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.multipledatasources.model.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
