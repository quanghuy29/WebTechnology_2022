<html>
    <head>
        <title>Insert</title>
    </head>
    <body>
        <form action = "insert_result.php" method = "GET">
            <table>
                <tr>
                    <td>Item Description: </td>
                    <td><input type="text" size="10" maxlength="50" name="item_desc"></td>
                </tr>
                <tr>
                    <td>Weight: </td>
                    <td><input type="text" size="10" maxlength="10" name="weight"></td>
                </tr>
                <tr>
                    <td>Cost: </td>
                    <td><input type="text" size="10" maxlength="10" name="cost"></td>
                </tr>
                <tr>
                    <td>Number Available: </td>
                    <td><input type="text" size="10" maxlength="10" name="num"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>