package com.intelli.utils;

/**
 * Created by Pooja on 17-02-2016.
 */
public class Constants {

    public static String INTELLIINVEST_SHARED_PREF = "INTELLIINVEST";
    public static String BASE_URL = "http://104.199.142.91/IntelliinvestWebApp/rest";

    public static String NETWORK_LOADING_MSG = "Loading...";
    public static String API_TYPE_POST = "POST";
    public static String API_TYPE_GET = "GET";

    public static String API_LOGIN = "LOGIN API";
    public static String API_REGISTER = "REGISTER API";
    public static String API_FORGOT_PASSWORD = "FORGOT PASSWORD API";
    public static String API_RESEND_ACTIVATION_CODE = "RESEND ACTIVATION CODE API";
    public static String API_GET_USERS = "GET USERS API";
    public static String API_GET_EMAIL = "GET EMAIL API";
    public static String API_UPDATE_PHONE = "UPDATE PHONE API";
    public static String API_DELETE_USER = "DELETE USER API";
    public static String API_ACTIVATE_USER = "ACTIVATE USER API";
    public static String API_GET_PORTFOLIO = "GET PORTFOLIO API";

    public static String URL_LOGIN = BASE_URL + "/user/login";
    public static String URL_REGISTER = BASE_URL + "/user/register";
    public static String URL_FORGOT_PASSWORD = BASE_URL + "/user/forgotPassword";
    public static String URL_RESEND_ACTIVATION_CODE = BASE_URL + "/user/";
    public static String URL_GET_USERS = BASE_URL + "/users";
    public static String URL_GET_EMAIL = BASE_URL + "/getEmail";
    public static String URL_UPDATE_PHONE = BASE_URL + "/user/update";
    public static String URL_DELETE_USER = BASE_URL + "/user/remove";
    public static String URL_ACTIVATE_USER = BASE_URL + "/user/activate";
    public static String URL_GET_STOCKS = BASE_URL + "/stock/getStocks";
    public static String URL_GET_STOCKS_PRICES = BASE_URL + "/stock/getStockPrices";
    public static String URL_GET_STOCK_FOR_CODE = BASE_URL + "/stock/getStockByCode";
    public static String URL_GET_STOCK_PRICE_FOR_CODE = BASE_URL + "/stock/getStockPriceByCode";
    public static String URL_ADD_PORTFOLIO_ITEMS = BASE_URL + "/stock/addPortfolioItemsForCode";
    public static String URL_GET_PORTFOLIO_NAMES = BASE_URL + "/stock/getPortfolioNames";
    public static String URL_GET_PORTFOLIO_ITEMS = BASE_URL + "/stock/getPortfolioItemsByCode";
    public static String URL_UPDATE_PORTFOLIO_ITEMS = BASE_URL + "/stock/updatePortfolioItemsForCode";
    public static String URL_DELETE_PORTFOLIO_ITEMS = BASE_URL + "/stock/deletePortfolioItemsForCode";
    public static String URL_DELETE_PORTFOLIO = BASE_URL + "/stock/deletePortfolio";
    public static String URL_GET_PORTFOLIO_SUMMARY = BASE_URL + "/stock/getPortfolioSummary";

    public static String ACTIVITY_ANALYSIS = "ANALYSIS";
    public static String ACTIVITY_LOGIN = "LOGIN";
    public static String ACTIVITY_REGISTER = "REGISTER";
    public static String ACTIVITY_FORGOT_PASSWORD = "FORGOT PASSWORD";
    public static String ACTIVITY_HOME = "HOME";
    public static String ACTIVITY_FEEDBACK = "FEEDBACK";
    public static String ACTIVITY_PORTFOLIO = "PORTFOLIO";
    public static String ACTIVITY_PERSONALIZE = "PERSONALIZE";
    public static String ACTIVITY_INDUSTRY = "INDUSTRY ANALYSIS";
    public static String ACTIVITY_FUNDAMENTAL = "FUNDAMENTAL ANALYSIS";
    public static String ACTIVITY_TECHNICAL = "TECHNICAL ANALYSIS";
    public static String ACTIVITY_BUBBLE = "BUBBLE ANALYSIS";
    public static String ACTIVITY_FORECAST = "FORECAST ANALYSIS";
    public static String ACTIVITY_MACRO = "MACRO ANALYSIS";
    public static String ACTIVITY_RISK = "RISK RETURN";
    public static String ACTIVITY_SUGGEST = "SUGGEST";
    public static String ACTIVITY_WATCHLIST = "WATCH LIST";

    public static String FRAGMENT_VIEW_CHART = "VIEW PORTFOLIO";
    public static String FRAGMENT_UPDATE_PORTFOLIO = "UPDATE PORTFOLIO";
    public static String FRAGMENT_ADD_PORTFOLIO = "ADD PORTFOLIO";

    public static String API_STATUS = "success";
    public static String API_ERROR = "error";
    public static String API_STATUS_SUCCESS = "true";
    public static String API_STATUS_FAIL = "false";
    public static String API_MESSAGE = "message";
}
