package com.nablarch.example.app.web.form;

import java.io.Serializable;

import nablarch.core.validation.ee.Domain;
import nablarch.core.validation.ee.Required;

public class ClientForm implements Serializable {

    // 顧客名
	@Required
    @Domain("clientName")
    private String clientName;

    // 業種コード
	@Required(message = "{nablarch.core.validation.ee.Required.select.message}")
    @Domain("industryCode")
    private String industryCode;

    /**
     * 顧客名を取得する。
     *
     * @return 顧客名。
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * 業種コードを取得する。
     *
     * @return 業種。
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 顧客名を設定する。
     *
     * @param clientName 設定したい顧客名。
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * 業種コードを設定する。
     *
     * @param industryCode 設定したい業種コード。
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

}