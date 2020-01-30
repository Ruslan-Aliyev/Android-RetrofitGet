# RetrofitGet

## Server:

http://ruslancode.net23.net/retrofit/1.9/get.php?test_name=test_one <sup>taken down</sup>

http://ruslancode.net23.net/retrofit/1.9/get.php?test_name=test_two <sup>taken down</sup>

```php
<?php
$res = array();
$res["test_one"] =  array('subject' => "test_1", 'info' => "test_info_1");
$res["test_two"] =  array('subject' => "test_2", 'info' => "test_info_2");
header('Content-Type: application/json');
echo json_encode($res[ $_GET["test_name"] ]);
?>
```

![](https://raw.githubusercontent.com/Ruslan-Aliyev/Android-RetrofitGet/master/Illustrations/1.png)
