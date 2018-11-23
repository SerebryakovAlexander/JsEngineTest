function ruleMain(inputObject)
{
    var rurPayment = JSON.parse(inputObject);

    var result = {};

    result.status = 'OK';
    result.code = 'VKNOK';
    result.message = null;

    var a1 = rurPayment.accountBen.substr(0,5);
    var a2 = rurPayment.accountCli.substr(0,5);

    if (['40702','12345','54321'].indexOf(a1) > -1)
    {
        result.code = 'VKOK';
    }

    if (['40702','12345','54321'].indexOf(a2) > -1)
    {
        result.code = 'VKOK';
    }

    return result;
}