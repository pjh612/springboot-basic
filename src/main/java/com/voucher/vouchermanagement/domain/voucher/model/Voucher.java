package com.voucher.vouchermanagement.domain.voucher.model;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Voucher {

    long discount(long beforeDiscount);

    UUID getVoucherId();

    Long getValue();

    LocalDateTime getCreatedAt();
}
