package example.patterndesign.strategydesign.strategy2;

/**
 * @author zhaoyu
 * @date 2019-08-14
 */
public class CardStrategy implements Strategy {

    @Override
    public Double calRecharge(Double charge, RechargeTypeEnum type) {
        return charge + charge * 0.01;
    }
}
