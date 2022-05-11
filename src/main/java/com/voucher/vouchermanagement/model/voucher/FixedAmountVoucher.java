package com.voucher.vouchermanagement.model.voucher;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class FixedAmountVoucher implements Voucher {

    private final UUID id;
    private long value;
    private final LocalDateTime createdAt;

    private static final long MIN_PERCENT = 1;

    public FixedAmountVoucher(UUID id, Long value, LocalDateTime createdAt) {
        validateValue(value);
        this.id = id;
        this.value = value;
        this.createdAt = createdAt.truncatedTo(ChronoUnit.MICROS);
    }

    @Override
    public long discount(long beforeDiscount) {
        if (beforeDiscount - value < 0) {
            return 0;
        }
        return beforeDiscount - value;
    }

    @Override
    public UUID getVoucherId() {
        return id;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "voucher id = " + id + ", amount = " + value + ", createdAt = " + createdAt;
    }

    private void validateValue(long value) {
        if (value < MIN_PERCENT) {
            throw new IllegalArgumentException("할인 금액이 1원 미만일 수 없습니다.");
        }
    }
}
