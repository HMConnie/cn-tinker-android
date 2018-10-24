package cn.connie.tinker.network.services;

import cn.connie.tinker.network.model.resp.currency.CurrencyAddResult;
import cn.connie.tinker.network.model.resp.currency.CurrencyInfoResult;
import cn.connie.tinker.network.model.resp.currency.CurrencyInformationMarketResult;
import cn.connie.tinker.network.model.resp.currency.CurrencyQuotationMarketResult;
import cn.connie.tinker.network.model.resp.currency.CurrencyNavigationBarResult;
import cn.connie.tinker.network.model.resp.currency.CurrencyTransactionPairMarketResult;
import cn.connie.tinker.network.model.resp.currency.CurrencyTrendInfoResult;
import cn.connie.tinker.network.model.resp.currency.ExchangeInfoResult;
import cn.connie.tinker.network.model.resp.currency.SearchCurrencyLeadResult;
import cn.connie.tinker.network.model.resp.currency.SearchCurrencyOrMoreResult;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by hinge on 2018/8/27.
 */

public interface CurrencyServices {

    /**
     * 币种导航栏
     */
    @GET("/market/currencyNavigationBar")
    Observable<CurrencyNavigationBarResult> currencyNavigationBar(@HeaderMap Map<String, String> headMap, @QueryMap(encoded = true) Map<String, String> map);

    /**
     * 行情接口
     */
    @POST("/market/getCurrencyMarket")
    @FormUrlEncoded
    Observable<CurrencyQuotationMarketResult> getCurrencyMarket(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);

    /**
     * 币种交易对行情
     */
    @POST("/market/currencyTransactionPairMarket")
    @FormUrlEncoded
    Observable<CurrencyTransactionPairMarketResult> currencyTransactionPairMarket(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);

    /**
     * 新增自选
     */
    @POST("/SelfSelection/add")
    @FormUrlEncoded
    Observable<CurrencyAddResult> add(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);

    /**
     * 删除自选
     */
    @GET("/SelfSelection/delete")
    Observable<Void> delete(@HeaderMap Map<String, String> headMap, @QueryMap(encoded = true) Map<String, String> map);

    /**
     * 置顶自选
     */
    @GET("/SelfSelection/SetTop")
    Observable<Void> setTop(@HeaderMap Map<String, String> headMap, @QueryMap(encoded = true) Map<String, String> map);

    /**
     * 币知咨询行情接口
     */
    @POST("/market/currencyInformationMarket")
    @FormUrlEncoded
    Observable<CurrencyInformationMarketResult> currencyInformationMarket(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);

    /**
     * 行情记录统计图
     */
    @GET("/currencyTrend/info")
    Observable<CurrencyTrendInfoResult> currencyTrendInfo(@HeaderMap Map<String, String> headMap, @QueryMap(encoded = true) Map<String, String> map);

    /**
     * 币种基本信息接口
     */
    @GET("/currency/info")
    Observable<CurrencyInfoResult> currencyInfo(@HeaderMap Map<String, String> headMap, @QueryMap(encoded = true) Map<String, String> map);

    /**
     * 币种基本信息接口
     */
    @POST("/currency/exchangeInfo")
    @FormUrlEncoded
    Observable<ExchangeInfoResult> exchangeInfo(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);

    /**
     * 币种基本信息接口
     */
    @GET("/searchCurrency/lead")
    Observable<SearchCurrencyLeadResult> lead(@HeaderMap Map<String, String> headMap);

    /**
     * 搜索货币/交易对接口
     */
    @POST("/searchCurrency/searchCurrencyOrMore")
    @FormUrlEncoded
    Observable<SearchCurrencyOrMoreResult> searchCurrencyOrMore(@HeaderMap Map<String, String> headMap, @FieldMap Map<String, String> map);
}
