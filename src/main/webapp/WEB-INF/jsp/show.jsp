<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>show</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


    <div class="container text-center mt-5">
        <div class="mt-5">
            <h3>최근 날씨 데이터</h3>
        </div>
        <div class="mt-5 mb-3">
            <b>매 시간 43분 갱신</b>
        </div>
        <div>
            <table class="table text-center">
                <thead>
                    <tr>
                        <th class="col-3">날짜</th>
                        <th class="col-3">기준 시간</th>
                        <th class="col-2">기온</th>
                        <th class="col-2">강수형태</th>
                        <th class="col-2">습도</th>
                    </tr>
                </thead>
                <tbody>
                <c:if test="${weather == null}">
                    <tr>
                        <td>데이터가 없습니다.</td>
                    </tr>
                </c:if>
                <c:if test="${weather != null}">
                <tr>
                    <td>${weather.yrmmdd}</td>
                    <td>${weather.hour}</td>
                    <td>${weather.temperature} ºC</td>
                    <td>${weather.precipitationForm}</td>
                    <td>${weather.humidity} %</td>
                </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>



</head>
</html>