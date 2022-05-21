package com.voucher.vouchermanagement.domain.voucher.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.voucher.vouchermanagement.domain.voucher.model.FixedAmountVoucher;
import com.voucher.vouchermanagement.domain.voucher.model.PercentDiscountVoucher;
import com.voucher.vouchermanagement.domain.voucher.model.Voucher;

class VoucherTest {

    @Test
    @DisplayName("FixedAmountVoucher는 최대 0원까지 할인이 된다.")
    void fixedAmountVoucherFailingDiscount() {
        //given
        Voucher voucher = new FixedAmountVoucher(UUID.randomUUID(), 100L, LocalDateTime.now());

        //when
        long finalPrice = voucher.discount(99);

        //then
        assertThat(finalPrice, is(0L));
    }

    @Test
    @DisplayName("FixedAmountVoucher는 100원 할인 테스트")
    void fixedAmountVoucherSuccessDiscountTest() {
        //given
        Voucher voucher = new FixedAmountVoucher(UUID.randomUUID(), 100L, LocalDateTime.now());

        //when
        long finalPrice = voucher.discount(150);

        //then
        assertThat(finalPrice, is(50L));
    }

    @Test
    @DisplayName("FixedAmountVoucher는 바우처 할인액이 1원 미만일 수 없다.")
    public void fixedAmountCannotDiscountUnderMinTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new FixedAmountVoucher(UUID.randomUUID(), 0L, LocalDateTime.now()));
    }

    @Test
    @DisplayName("percentDiscountVoucher 50% 할인 테스트")
    void percentDiscountVoucher50PercentDiscountTest() {
        //given
        Voucher voucher = new PercentDiscountVoucher(UUID.randomUUID(), 50L, LocalDateTime.now());

        //when
        long finalPrice = voucher.discount(100L);

        //then
        assertThat(finalPrice, is(50L));
    }

    @Test
    @DisplayName("percentDiscountVoucher 100% 할인 테스트")
    void percentDiscountVoucher100PercentDiscountTest() {
        //given
        Voucher voucher = new PercentDiscountVoucher(UUID.randomUUID(), 100L, LocalDateTime.now());

        //when
        long finalPrice = voucher.discount(100L);

        //then
        assertThat(finalPrice, is(0L));
    }

    @Test
    @DisplayName("percentDiscountVoucher는 0% 할인은 안된다.")
    void percentDiscountVoucherCannotDiscountUnderMinTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new PercentDiscountVoucher(UUID.randomUUID(), 0L, LocalDateTime.now()));
    }

    @Test
    @DisplayName("percentDiscountVoucher는 100% 초과 할인은 안된다.")
    void percentDiscountVoucherCannotDiscountOverMaxTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new PercentDiscountVoucher(UUID.randomUUID(), 101L, LocalDateTime.now()));
    }
}