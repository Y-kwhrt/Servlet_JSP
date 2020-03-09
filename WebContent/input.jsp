<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" 
    import="java.util.ArrayList" import="java.util.Iterator" 
    import="java.util.List" import="java.util.Arrays"%>
    <% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>書籍登録画面</title>
<!--ver:0.0.2-->
</head>
<body>
 <h2>■書籍在庫管理システム</h2>
 　<h3>  【書籍登録画面】</h3>
 　<form action="./dissts" method="get">
  <table>
  	<% 
  		String err_con = String.valueOf(request.getAttribute("err"));
  		if(err_con == "null"){
  			err_con = "";
  		}
  	%>
    <p><%= err_con %></p>
    <% 
    	List<String> in_name = new ArrayList<>(Arrays.asList("title","author","publi","price"));
    	List<String> out_name = new ArrayList<>();
    	for(int i = 0; i < in_name.size(); i++){
    		out_name.add(String.valueOf(request.getAttribute(in_name.get(i))));
        	if(out_name.get(i) == "null"){
        		 out_name.set(i,"");
        	}
    	}
    %>
  	<tr><td></td><td>タイトル</td><td><input type="text" name="title" value = "<%= out_name.get(0) %>"/></td></tr>
  	<tr><td></td><td>著者</td><td><input type="text" name="author" value = "<%= out_name.get(1)%>"/></td></tr>
  	<tr><td></td><td>出版社</td><td><input type="text" name="publi" value = "<%= out_name.get(2) %>"/></td></tr>
  	<tr><td></td><td>価格</td><td><input type="text" name="price" value = "<%= out_name.get(3) %>"/></td></tr>
  	<tr><td></td><td>ジャンル</td><td>
  	<%
  	String[] strJan = {"文芸","実用","ビジネス","教養","趣味"};
  	
  	for(int i = 0; i < strJan.length; i++) {		
		String ind = String.valueOf(i);
		String genres = String.valueOf(request.getAttribute("genre" + ind));
		if(genres != null) {
			if(genres.equals("1")) {
			%>
			<input type=checkbox name=<%= "genre" + ind %> checked=checked value=<%= i %>/><%= strJan[i]%>
			<%//checked="checked"で選択状態
			}else {
				%>
				<input type=checkbox name=<%= "genre" + ind %> value=<%= i %>/><%= strJan[i]%>
				<% 
				}
			}
		} 
		%>
  		</td></tr>
  	<tr><td></td><td>在庫</td><td>
  	<%
  	String radios = String.valueOf(request.getAttribute("radios"));
  	if(radios.equals("1") || radios == "1" || radios.equals("null")) {
	%>
		<input type=radio name=stock value=1 checked/>あり
		<input type=radio name=stock value=0 />なし</td></tr>
	<%
	}else {
	%>
		<input type=radio name=stock value=1 />あり
		<input type=radio name=stock value=0 checked/>なし</td></tr>
	<% 
	}
  	String rem = String.valueOf(request.getAttribute("remarks"));
  	if(rem.equals("null")){
  		rem = "";
  	}
  	%>
  	<tr><td></td><td>備考</td><td><textarea name="remarks" cols="40" rows="4"><%=rem%></textarea></td></tr>
  	<tr><td></td><td height="30"></td><td></td><td align="right">
  	<input type="submit" value="登録"/></td></tr>
  </table>
 </form>
</body>
</html>