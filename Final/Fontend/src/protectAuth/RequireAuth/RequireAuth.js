import React from "react";
import { useLocation, Navigate, Outlet } from "react-router-dom";

const RequireAuth = ({ allowedRoles }) => {
    const location = useLocation();
    const token = localStorage.getItem('token-auth')
    const isLogin = localStorage.getItem('isLoggedIn')

    if (isLogin === true || token != null) {
        return <Outlet />
    } else {
        //if (auth?.token) {
        //    return <Navigate to="/oops" state={{ from: location }} replace />
        //} else {
            return <Navigate to="/login" state={{ from: location }} replace />
        //}
    }

    // return (
    //     auth.role == allowedRoles
    //         ? <Outlet />
    //         : auth?.user
    //             ? <Navigate to="/oops" state={{ from: location }} replace />
    //             : <Navigate to="/login" state={{ from: location }} replace />
    // );
}

export default RequireAuth;