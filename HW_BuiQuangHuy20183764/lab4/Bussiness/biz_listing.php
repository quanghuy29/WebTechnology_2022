<html>
    <head>
        <title>Business Listings</title>
    </head>
    <body>
            <?php  
                $server = '127.0.0.1';
                $user = 'root';
                $pass = '';
                $mydb = 'business_service';
                $table_name = 'Category';
                $table_buz = 'Businesses';
                $table_buzcate = 'Biz_Category';
                $connect = mysqli_connect($server, $user, $pass, $mydb);  
                $SQLcmd = "SELECT * FROM $table_name";   
                mysqli_select_db($connect, $mydb);
                $result = mysqli_query($connect, $SQLcmd);            
            ?>
            <font size = "10">Business Listings<br></font>

            <table>
                <tr>
                    <td>
                        <table border="1">
                            <tr>
                                <td><b>Click on a category to find business listings: </b></td>
                            </tr>    
                            <?php
                                while ($row = mysqli_fetch_array($result)){
                                    $link = "biz_listing.php?id=".$row["CategoryID"];
                                    print "<tr><td><a href=".$link.">".$row["Title"]."</a></td></tr>";             
                                }                     
                            ?>
                        </table> 
                    </td>
                    <td>
                        <table border="1">
                            <?php
                            if(array_key_exists("id", $_GET)){
                                $id = $_GET["id"];
                                $SQLsearchID = "SELECT * FROM $table_buzcate WHERE (CategoryID = '$id')";
                                $result_cat = mysqli_query($connect, $SQLsearchID);
                                while ($row_cat = mysqli_fetch_array($result_cat)){
                                    $buzID = $row_cat["BusinessID"];
                                    $SQLsearchBuz = "SELECT * FROM $table_buz WHERE (BusinessID = '$buzID')";
                                    $result_buz = mysqli_query($connect, $SQLsearchBuz);
                                    while ($row_buz = mysqli_fetch_array($result_buz)){
                                        print "<tr>"
                                            . "<td>".$row_buz["BusinessID"]."</td>"
                                            . "<td>".$row_buz["Name"]."</td>"
                                            . "<td>".$row_buz["Address"]."</td>"
                                            . "<td>".$row_buz["City"]."</td>"   
                                            . "<td>".$row_buz["Telephone"]."</td>"
                                            . "<td>".$row_buz["URL"]."</td>"               
                                            . "</tr>";
                                    }
                                }
                                mysqli_close($connect);
                            }
                            else {
                                mysqli_close($connect);
                            }
                            ?>
                        </table>
                    </td>
                </tr>
            </table>       
    </body>
</html>