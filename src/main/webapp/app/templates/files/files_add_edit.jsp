<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<% BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService(); %>
<div ng-controller="filesCtrl as vm"  class="container wow fadeInUp">
    Add image to {{vm.obj.name}}
    <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
        <input type="hidden" name="itemId" value="{{vm.obj.id}}">
        <input type="file" name="myFile" class="btn btn-default">
        <br/>
        <input type="submit" value="Submit" class="btn btn-default btn-lg">
    </form>
</div>