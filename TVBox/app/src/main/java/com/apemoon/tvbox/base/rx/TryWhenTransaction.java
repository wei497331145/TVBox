package com.apemoon.tvbox.base.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

/**
 * @Author water
 * @Date 2017-11-24
 * @des  网络异常的处理类
 */
public class TryWhenTransaction implements Function<Observable<? extends Throwable>, Observable<?>> {

    private static final String TAG = "TokenAdvancedFragment";
    /***
     * 重试间隔时间
     */
    private long mInterval;

    public TryWhenTransaction(long interval) {
        mInterval = interval;
    }

    private int retryCount = 0;
    private int TimeTokenInvalidCount = 0;

    @Override
    public Observable<?> apply(@NonNull Observable<? extends Throwable> observable) throws Exception {
        return observable
                .zipWith(Observable.range(1, 3), new BiFunction<Throwable, Integer, Throwable>() {
                    @Override
                    public Throwable apply(@NonNull Throwable throwable, @NonNull Integer integer) throws Exception {
                        return throwable;
                    }
                })
                .flatMap(new Function<Throwable, ObservableSource<?>>() {

                    @Override
                    public ObservableSource<?> apply(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (throwable instanceof HttpException) {
                            //若没打开网络则停止重试
                            return Observable.error(new IllegalArgumentException("网络错误"));
                        } else if (throwable instanceof NullPointerException) {
                            //重试三次
                        } /*else if (throwable instanceof LongTokenInvalidException) {
                            return Observable.error(new LongTokenInvalidException());
                        } else if (throwable instanceof BannedInvalidException) {
                            return Observable.error(new BannedInvalidException());
                        } */
                        /*else if (throwable instanceof TimeTokenInvalidException) {
                                String longToken = PreferencesUtils.getString(GlobalUtil.mContext, Constant.LONG_TOKEN);
                                return RetrofitApi.getInstance().getTiemToken1(longToken)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .doOnNext(new Consumer<HttpResultBody<Token>>() {
                                            @Override
                                            public void accept(@NonNull HttpResultBody<Token> tokenHttpResultBody) throws Exception {
                                                String timeToken = tokenHttpResultBody.data.timetoken;
                                                PreferencesUtils.putString(GlobalUtil.mContext, Constant.TIME_TOKEN, timeToken);
                                                String sign = MD5Encoder.encodeByMd5(timeToken + "ito");
                                                GlobalUtil.sign = sign;
                                                PreferencesUtils.putString(GlobalUtil.mContext, Constant.SIGN, sign);

                                            }
                                        });
                            }*/
                            return Observable.error(new IllegalArgumentException("网络访问超过最大次数"));//超过最大次数终

                    }
                });
    }
}