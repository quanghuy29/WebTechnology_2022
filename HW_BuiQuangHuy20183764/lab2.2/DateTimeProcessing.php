<html>
    <head><title>Date Time Processing</title></head>
    <body>
        Enter your name and select date and time for the appointment <br>
        <form action="<?php echo $_SERVER["PHP_SELF"]?>" method="GET">
            <?php
                if(array_key_exists("name", $_GET)){
                    $name = $_GET["name"];
                    $day = $_GET["day"];
                    $month = $_GET["month"];
                    $year = $_GET["year"];
                    $hour = $_GET["hour"];
                    $minute = $_GET["minute"];
                    $second = $_GET["second"];
                } else{
                    $name = "";
                    $day = 1;
                    $month = 1;
                    $year = 1900;
                    $hour = 1;
                    $minute = 1;
                    $second = 1;
                }
            ?>
            <table>
                <tr>
                    <td>Your name: </td>
                    <td><input type="text" size="10" maxlength="15" name="name" value="<?php echo $name?>"></td>
                </tr>
                <tr>
                    <td>Date:</td>
                    <td>
                        <select name="day">
                            <?php
                                for($i = 1; $i<32; $i++){
                                    if ($day==$i){
                                        if ($day == 31 && ($month == 4 || $month == 6 || $month == 9 || $month == 11)){
                                            $day = 30;                                     
                                        }
                                        if (($year % 100 == 0 && $year % 400 == 0) || $year % 4 == 0 && $year % 10 != 0)
                                        {
                                            if(($day == 30 || $day == 31) && $month == 2)   
                                                {
                                                    $day = 29;
                                                }
                                        } else {
                                            if(($day == 30 || $day == 31 || $day == 29) && $month == 2)   
                                                {
                                                    $day = 28;
                                                }
                                        }
                                        print ("<option selected>$i</option>");
                                    } else {
                                    print ("<option>$i</option>");
                                    }
                                }
                            ?>
                        </select>
                    </td>
                    <td>
                        <select name="month">
                            <?php
                                for($i = 1; $i<13; $i++){
                                    if ($month==$i){
                                        print ("<option selected>$i</option>");
                                    } else {
                                    print ("<option>$i</option>");
                                    }
                                }
                            ?>
                        </select>
                    </td>
                    <td>
                        <select name="year">
                            <?php
                                for($i = 1900; $i<2022; $i++){
                                    if ($year==$i){
                                        print ("<option selected>$i</option>");
                                    } else {
                                    print ("<option>$i</option>");
                                    }
                                }
                            ?>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Time: </td>
                    <td>
                        <select name="hour">
                            <?php
                                for($i = 1; $i<25; $i++){
                                    if ($hour==$i){
                                        print ("<option selected>$i</option>");
                                    } else {
                                    print ("<option>$i</option>");
                                    }
                                }
                            ?>
                        </select>
                    </td>
                    <td>
                        <select name="minute">
                            <?php
                                for($i = 1; $i<61; $i++){
                                    if ($minute==$i){
                                        print ("<option selected>$i</option>");
                                    } else {
                                    print ("<option>$i</option>");
                                    }
                                }
                            ?>
                        </select>
                    </td>
                    <td>
                        <select name="second">
                            <?php
                                for($i = 1; $i<61; $i++){
                                    if ($second==$i){
                                        print ("<option selected>$i</option>");
                                    } else {
                                    print ("<option>$i</option>");
                                    }
                                }
                            ?>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right"> <input type="SUBMIT" value="Submit"></td><!-- comment -->
                    <td align="left"> <input type="RESET" value="Reset"></td>
                </tr>
            </table>
            <?php
                if(array_key_exists("name", $_GET)){
                    
                    
                    print ("Hi $name! <br>"); 
                    print ("You have choose to have an appointment on $hour:$minute:$second, $day/$month/$year <br><br>");
                    print ("More information <br><br>");
                    print ("In 12 hours, the time and date is ");
                    $date=date_create("$year-$month-$day $hour:$minute:$second");
			echo date_format($date,"h:i:s A, d/m/Y");
			echo "<br><br>";

			if($month == 1 || $month == 3 || $month == 5 || $month == 7 || $month == 8 || $month == 10 || $month == 12){
				echo "This month has 31 days";
			} elseif ($month != 2){
				echo "This month has 30 days";
			} else {
				if($year % 100 == 0 && $year % 400 == 0){
					echo "This month has 29 days";
				} elseif ($year % 4 == 0 && $year % 10 != 0) {
					echo "This month has 29 days";
				} else {
					echo "This month has 28 days";
				}
			}
                }
            ?>
        </form>
    </body>
</html>

