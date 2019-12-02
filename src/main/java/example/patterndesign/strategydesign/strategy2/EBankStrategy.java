package example.patterndesign.strategydesign.strategy2;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class EBankStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge * 0.85;
    }
}
