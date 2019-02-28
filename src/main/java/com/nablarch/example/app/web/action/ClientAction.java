package com.nablarch.example.app.web.action;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nablarch.example.app.entity.Client;
import com.nablarch.example.app.entity.Industry;
import com.nablarch.example.app.web.dto.ClientDto;
import com.nablarch.example.app.web.dto.ClientSearchDto;
import com.nablarch.example.app.web.form.ClientForm;
import com.nablarch.example.app.web.form.ClientSearchForm;

import nablarch.common.dao.EntityList;
import nablarch.common.dao.UniversalDao;
import nablarch.common.web.interceptor.InjectForm;
import nablarch.common.web.session.SessionUtil;
import nablarch.common.web.token.OnDoubleSubmission;
import nablarch.core.beans.BeanUtil;
import nablarch.core.message.ApplicationException;
import nablarch.core.validation.ee.ValidatorUtil;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpResponse;
import nablarch.fw.web.interceptor.OnError;

/**
 * 顧客検索API
 *
 * @author Nabu Rakutaro
 */
public class ClientAction {

    /**
     * 指定された条件に合致する顧客を検索する。
     *
     * @param req HTTPリクエスト
     * @return 顧客情報リスト
     */
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClientDto> find(HttpRequest req) {
        final ClientSearchForm form = BeanUtil.createAndCopy(ClientSearchForm.class, req.getParamMap());

        // Beanバリデーション実行
        ValidatorUtil.validate(form);

        final ClientSearchDto condition = BeanUtil.createAndCopy(ClientSearchDto.class, form);
        return UniversalDao.findAllBySqlFile(ClientDto.class, "SEARCH_CLIENT", condition);
    }

    public HttpResponse back(HttpRequest request, ExecutionContext context) {

        Client client = SessionUtil.get(context, "client");

        ClientForm form = BeanUtil.createAndCopy(ClientForm.class, client);
        context.setRequestScopedVar("form", form);

        return new HttpResponse("forward://input");
    }

    /**
     * 顧客情報を登録する。
     *
     * @param request
     * @param context
     * @return
     */
    public HttpResponse input(HttpRequest request, ExecutionContext context) {

        SessionUtil.delete(context, "client");

        EntityList<Industry> industries = UniversalDao.findAll(Industry.class);
        context.setRequestScopedVar("industries", industries);

        return new HttpResponse("/WEB-INF/view/client/create.jsp");
    }

    @OnDoubleSubmission
    public HttpResponse create(HttpRequest request, ExecutionContext context) {

        Client client = SessionUtil.get(context, "client");

        UniversalDao.insert(client);

        SessionUtil.delete(context, "client");

        return new HttpResponse(303, "redirect://complete");
    }

    /**
    * 登録確認画面を表示する。
    * @param request HTTPリクエスト
    * @param context 実行コンテキスト
    * @return HTTPレスポンス
    */
    @InjectForm(form = ClientForm.class, prefix = "form")
    @OnError(type = ApplicationException.class, path = "forward://input")
    public HttpResponse confirm(HttpRequest request, ExecutionContext context) {

        // バリデーション済みオブジェクトを取得
        ClientForm form = context.getRequestScopedVar("form");

        Client client = BeanUtil.createAndCopy(Client.class, form);
        SessionUtil.put(context, "client", client);

        EntityList<Industry> industries = UniversalDao.findAll(Industry.class);
        context.setRequestScopedVar("industries", industries);

        return new HttpResponse("/WEB-INF/view/client/confirm.jsp");
    }

    public HttpResponse complete(HttpRequest request, ExecutionContext context) {
        return new HttpResponse("/WEB-INF/view/client/complete.jsp");
    }
}
