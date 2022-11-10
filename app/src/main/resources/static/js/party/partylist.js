let region_sido = "0";
let region_sigu = "0";
let sports = "0";
let partyDate = "0";
let partyTime = "0";
let searchText = "0";
let listStar = "0";
let listCreate = "0";
let listPartyDate = "0";

let btnValue = "";


$("#region_sido").change(() => {
  loadList(getListConditions())
});

$("#region_sigu").change(() => {
  loadList(getListConditions())
});

$("#sports_name").change(() => {
  loadList(getListConditions())
});

$("#partyDate").change(() => {
  loadList(getListConditions())
});

$("#partyTime").change(() => {
  loadList(getListConditions())
});

$("#searchBtn").click(() => {
  loadList(getListConditions())
});

$("#list_reset").click(() => {
  $("#list_star").attr('value','0');
  $("#list_create").attr('value','0');
  $("#list_partyDate").attr('value','0');
  console.log($("#list_star").val())
  console.log($("#list_create").val())
  console.log($("#list_partyDate").val())
  console.log("구분")
  loadList(getListConditions())
});

$("#list_star").click(() => {
  console.log($("#list_star").attr('id'))
  changeBtnValue($("#list_star").attr('id'), $("#list_star").val())
  loadList(getListConditions())
});

$("#list_create").click(() => {
  changeBtnValue($("#list_create").attr('id'), $("#list_create").val())
  loadList(getListConditions())
});

$("#list_partyDate").click(() => {
  changeBtnValue($("#list_partyDate").attr('id'), $("#list_partyDate").val())
  loadList(getListConditions())
});

$("#test1").click(function test() {
  function test(){
  
  }
});



function changeBtnValue(btnId, btnValue) {
  if (btnValue == 0) {
    $("#" + btnId).attr('value','1');
    $("#" + btnId).css('background-color','gray');
  }
  if (btnValue == 1) {
    $("#" + btnId).attr('value','0');
    $("#" + btnId).css('background-color','white');
  }
}

function getListConditions() {
  var params = {}
  if ($("#region_sido").val() != "0") {
    params.si = $("#region_sido").val();
  }
  if ($("#region_sigu").val() != "0") {
    params.gu = $("#region_sigu").val();
  }
  if ($("#sports_name").val() != "0") {
    params.sports = $("#sports_name").val();
  }
  if ($("#partyDate").val() != "") {
    params.partyDate = $("#partyDate").val();
  }
  if ($("#partyTime").val() != "") {
    params.partyTime = $("#partyTime").val();
  }
  if ($("#list_search").val() != "") {
    params.searchText = $("#list_search").val();
  }
  if ($("#list_star").val() != "") {
    params.listStar = $("#list_star").val();
  }
  if ($("#list_create").val() != "") {
    params.listCreate = $("#list_create").val();
  }
  if ($("#list_partyDate").val() != "") {
    params.listPartyDate = $("#list_partyDate").val();
 }
  if ($("#list_star").val() != "" && $("#list_create").val() != "") {
    params.listStar = $("#list_star").val();
    params.listCreate = $("#list_create").val();
  }
  if ($("#list_star").val() != "" && $("#list_partyDate").val() != "") {
    params.listStar = $("#list_star").val();
    params.listPartyDate = $("#list_partyDate").val();
  }
  if ($("#list_create").val() != "" && $("#list_partyDate").val() != "") {
    params.listCreate = $("#list_create").val();
    params.listPartyDate = $("#list_partyDate").val();
  }
  if ($("#list_star").val() != "" && $("#list_create").val() != "" && $("#list_partyDate").val() == "1") {
    params.listStar = $("#list_star").val();
    params.listCreate = $("#list_create").val();
    params.listPartyDate = $("#list_partyDate").val();

  }
  return params;
}

function loadList(params) {
  
  console.log(params);
  
  $.ajax({
    type: "GET",
    url: "/app/party/list-ajax",
    data: params,
    success: function(result) {
        
      console.log(result);
      $('#partyList').html(result);

      }
  });

}