package com.sun.sunboard.common;

import javax.servlet.http.HttpSession;

    public class SessionUtil {

        private static final String LOGIN_USER_ID = "LOGIN_USER_ID";

        /**
         * 세션에 로그인 사용자의 ID를 저장
         *
         * @param session HttpSession 객체로, 세션에 데이터를 저장하기 위한 용도로 사용됩니다.
         * @param userId 사용자의 ID로, 세션에 저장될 값입니다.
         */
        public static void setLoginUserId(HttpSession session, String userId) {
            session.setAttribute(LOGIN_USER_ID, userId);
        }

        /**
         * 세션에서 로그인 사용자의 ID를 반환
         *
         * @param session HttpSession 객체로, 세션에 데이터를 가져오기 위한 용도로 사용됩니다.
         * @return String 타입의 사용자 ID 값. 만약 세션에 해당 속성이 존재하지 않으면 null을 반환합니다.
         */
        public static String getLoginUserId(HttpSession session) {
            String userId = (String) session.getAttribute(LOGIN_USER_ID);
            return userId == null ? null : userId;
        }

        /**
         * 세션에서 로그인 사용자의 ID를 삭제
         *
         * @param session HttpSession 객체로, 세션에 데이터를 삭제하기 위한 용도로 사용됩니다.
         */
        public static void logoutUser(HttpSession session) {
            session.removeAttribute(LOGIN_USER_ID);
        }
    }
