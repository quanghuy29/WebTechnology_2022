<html>

<head>
    <title>Bussiness Registration</title>
</head>

<body>
    <form action = "<?php echo $_SERVER["PHP_SELF"]?>" method = "GET">
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
        if (array_key_exists("category", $_GET) && array_key_exists("buzName", $_GET)){
            $name = $_GET["buzName"];
            $address = $_GET["buzAddress"];
            $city = $_GET["buzCity"];
            $telephone = $_GET["buzTelephone"];
            $url = $_GET["buzURL"];
            $category = $_GET["category"];
            $state = true;

            $SQLInsertBuz = "INSERT INTO $table_buz VALUES (NULL, '$name', '$address', '$city', '$telephone', '$url')";
            $SQLSearch = "SELECT * FROM $table_buz WHERE (Name = '$name')";
            mysqli_query($connect, $SQLInsertBuz);
            $result = mysqli_query($connect, $SQLSearch);
            $row = mysqli_fetch_array($result);
            $id = $row["BusinessID"];
            foreach ($category as $cate){
                $SQLInsertBuzCat = "INSERT INTO $table_buzcate VALUES (NULL,'$cate','$id')";
                mysqli_query($connect, $SQLInsertBuzCat);
            }
        } else {
            $name = "";
            $address = "";
            $city = "";
            $telephone = "";
            $url = "";
            $category = "";        
            $state = false;
        }
       
    ?>
    <font size = "10">Bussiness Registration<br></font>

    <?php if($state == true) print "Record inserted as shown below" ?>

    <table>
        <tr>
            <td>
                <?php if($state == false) print "Click on one, or control-click on multiple categories: <br>"; 
                    else print "Selected category values are highlighted: <br>";
                ?>           
                <select name = "category[]" size="8" 
                    <?php if($state==true) print "disabled multiple";
                    else print "multiple";?>>
                    <?php
                        $result = mysqli_query($connect, $SQLcmd);
                        if($state == false) {
                            while ($row = mysqli_fetch_array($result)){  
                                    $title = $row["CategoryID"];                                             
                                    print "<option value = "."$title"." >".$row["Title"]."</option>";
                                }                        
                            }
                        else {
                            while ($row = mysqli_fetch_array($result)){    
                                $done = false;
                                $title = $row["CategoryID"];              
                                foreach($_GET["category"] as $value){                           
                                    if (strcmp($value, $title) == 0){
                                        print "<option value = ".$row["Title"]." selected>".$row["Title"]."</option>";
                                        $done = true;
                                        break;
                                    }                                   
                                }     
                                if ($done == true) continue;
                                print "<option value = ".$row["Title"].">".$row["Title"]."</option>";                   
                            }
                        }            
                        mysqli_close($connect);           
                    ?>
                </select>
            </td>
            <td>
                <table>
                    <tr>
                        <td>Bussiness Name: </td>
                        <td><input type = "text" size = "40" maxlength = "50" name = "buzName" value = "<?php echo $name?>"></td>
                    </tr>
                    <tr>
                        <td>Address: </td>
                        <td><input type = "text" size = "40" maxlength = "50" name = "buzAddress" value = "<?php echo $address?>"></td>
                    </tr>
                    <tr>
                        <td>City: </td>
                        <td><input type = "text" size = "40" maxlength = "50" name = "buzCity" value = "<?php echo $city?>"></td>
                    </tr>
                    <tr>
                        <td>Telephone: </td>
                        <td><input type = "text" size = "40" maxlength = "50" name = "buzTelephone" value = "<?php echo $telephone?>"></td>
                    </tr>
                    <tr>
                        <td>URL: </td>
                        <td><input type = "text" size = "40" maxlength = "50" name = "buzURL" value = "<?php echo $url?>"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
            <?php if($state == true) $add = "Add Another Bussiness";
                else $add = "Add Bussiness"; ?>
            <input type = "submit" value = "<?php echo $add?>">
    </form>
</body>

</html>
