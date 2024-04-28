package com.loukwn.biocompose

@JsFun("() => { var date = new Date(); return String(date.getHours()).padStart(2, '0') + \":\" + String(date.getMinutes()).padStart(2, '0') }")
actual external fun getFormattedTime(): String