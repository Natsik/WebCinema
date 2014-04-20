Number.prototype.toHHMM = function() {
    var hours   = Math.floor(this / 3600);
    var minutes = Math.floor((this - (hours * 3600)) / 60);

    if (hours   < 10) {hours   = "0" + hours;}
    if (minutes < 10) {minutes = "0" + minutes;}
    return hours + ':' + minutes;
};

String.prototype.toHHMM = function () {
    var sec_num = parseInt(this, 10); // don't forget the second param
    return sec_num.toHHMM()
};

String.prototype.cutHHMM = function () {
    return this.slice(11, 16)
};

$(document).ready(function() {
    $(".convertToHHMM").each(function(){
        $(this).html(($(this).html()).toHHMM());
    });

    $(".cutHHMM").each(function(){
        $(this).html(($(this).html()).cutHHMM());
    });
});