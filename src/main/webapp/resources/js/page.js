require.config({
  paths : {
    bootstrap : 'vendor/bootstrap',
    modernizr : 'vendor/modernizr'
  },
  shim : {
    bootstrap : {
      deps : [ 'jquery', 'modernizr' ]
    }
  }
});

require([ 'bootstrap' ], function(Bootstrap) {
  $(document).ready(function() {

    function highlightActiveTab() {
      var pathPrefix = "/webapp-base";

      var path = window.location.pathname;
      var appPath = path.substring(pathPrefix.length);

      var linkIdToHighlight = "homeLink";

      if (appPath === "/admin/migrations") {
        linkIdToHighlight = "adminMigrationsLink";
      } else if (appPath === "/admin/schema") {
        linkIdToHighlight = "adminSchemaLink";
      }

      $("#" + linkIdToHighlight).addClass("active");
    }

    highlightActiveTab();
  });
});
