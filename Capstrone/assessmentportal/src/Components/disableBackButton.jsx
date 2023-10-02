import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
function DisableBackButton() {
  
  const location = useLocation();
  useEffect(()=>{
  //   if (location.pathname === `/UserDashBoard` || location.pathname === `/Category` || location.pathname === `/profile` || location.pathname === `/Quiz`)
    window.history.pushState(null, '', window.location.href);
    window.onpopstate = () => {
      window.history.pushState(null, '', window.location.href);
    }
    });
  }
export default DisableBackButton;