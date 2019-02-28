<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="n" uri="http://tis.co.jp/nablarch" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
    <head>
        <title>顧客登録画面</title>
    </head>
    <body>
        <n:include path="/WEB-INF/view/common/menu.jsp" />
        <n:include path="/WEB-INF/view/common/header.jsp" />
        <div class="container-fluid mainContents">
            <section class="row">
                <div class="title-nav">
                    <span class="page-title">顧客登録画面</span>
                </div>
            </section>
        </div>

        <!-- ここに登録画面の初期表示部分を実装する -->
        <n:form>
    		<div class="form-group label-static is-empty">
        		<label class="control-label">顧客名</label>
        		<!-- 顧客名のテキストボックス -->
          		<n:text name="form.clientName"
                	cssClass="form-control input-text" errorCss="form-control input-error" />
        		<!-- 顧客名の入力エラー時のエラーメッセージ -->
        		<n:error errorCss="message-error" name="form.clientName" />
    		</div>
    		<div class="form-group label-static is-empty">
        		<label class="control-label">業種</label>
        		<!-- 業種のプルダウン -->
            	<n:select
                	listName="industries"
                	elementValueProperty="industryCode"
                	elementLabelProperty="industryName"
                	name="form.industryCode"
                	withNoneOption="true"
                	cssClass="btn dropdown-toggle"
                	errorCss="btn dropdown-toggle input-error" />
        		<!-- 業種の入力エラー時のエラーメッセージ -->
        		<n:error errorCss="message-error" name="form.industryCode" />
    		</div>
    		<div class="button-nav">
            	<!-- 登録ボタンは登録画面でのみ表示 -->
    			<n:forInputPage>
        			<n:button uri="/action/client/confirm"
                  	cssClass="btn btn-raised btn-success">登録</n:button>
    			</n:forInputPage>
    			<!-- 入力へ戻る、確定ボタンは確認画面でのみ表示 -->
    			<n:forConfirmationPage>
        		<n:button uri="/action/client/back"
					cssClass="btn btn-raised btn-default">入力へ戻る</n:button>
				<!-- allowDoubleSubmission属性にfalseを指定する -->
        		<n:button uri="/action/client/create"
        			allowDoubleSubmission="false"
					cssClass="btn btn-raised btn-success">確定</n:button>
    			</n:forConfirmationPage>
    		</div>
		</n:form>

        <n:include path="/WEB-INF/view/common/footer.jsp" />
    </body>
</html>