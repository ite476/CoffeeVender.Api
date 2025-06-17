package com.coffeevender.core.user.error

import com.coffeevender.core.common.DomainError

sealed class UserError(
    override val message: String) : DomainError{

    data class UserNotFound(
        val userId: Long) 
    : UserError("사용자 코드 $userId 에 해당하는 사용자가 없습니다.")

    data class UserAlreadyExists(
        val userLoginId: String) 
    : UserError("사용자 로그인 아이디 $userLoginId 에 해당하는 사용자가 이미 존재합니다.")

    data class UserLoginIdNotValid(
        val loginId: String) 
    : UserError("사용자 로그인 아이디 양식이 올바르지 않습니다. $loginId")

    data class UserPasswordNotValid(
        val password: String) 
    : UserError("사용자 비밀번호 양식이 올바르지 않습니다. $password")
}