package org.prgrms.kdt;

import java.util.UUID;

public class PercentDiscountVoucher implements Voucher{
    private UUID voucherId;
    private final long percent;

    public PercentDiscountVoucher(UUID voucherId, long percent) {
        this.voucherId = voucherId;
        this.percent = percent;
    }

    @Override
    public UUID getVoucherId() {
        return null;
    }

    @Override
    public long discount(long beforeDiscount) {
        return beforeDiscount * (percent / 100); // 여기 틀린 것 같음.
    }
}