function helloFromJavascript() {
    alert("hello from javascript");
  }
  
  async function helloFromJava() {
    //this allow us to talk to the server by fetching to their available endpoint
    const response = await fetch("http://localhost:8080/hello");
    if (!response.ok) {
      alert("something wrong! have you run the server yet?");
      return;
    }
    const text = await response.text();
    alert(text);
  }