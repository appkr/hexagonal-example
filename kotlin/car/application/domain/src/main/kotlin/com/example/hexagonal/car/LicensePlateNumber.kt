package com.example.hexagonal.car

@JvmInline
value class LicensePlateNumber(
    private val value: String,
) {
    init {
        require(SPEC_LICENCE_PLATE_NUMBER.matches(value)) {
            "자동차 번호 형식이 유효하지 않습니다"
        }
    }

    companion object {
        // 외교, 국회, 영사 등은 생략한다
        val SPEC_LICENCE_PLATE_NUMBER = "[가-힣]{0,2}\\s?[0-9]{1,3}\\s?[가-힣]\\s?[0-9]{4}".toRegex()
    }
}
