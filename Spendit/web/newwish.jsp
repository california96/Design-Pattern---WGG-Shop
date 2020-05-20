<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file = "navbar.jsp"%>
<style>
    .btn-submit{
    background-color: #73C2FB;
    color: white;
    float: right;
  }
  </style>
 <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Add a Wish</h1>             
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="index.jsp">Home</a></li>
              <li class="breadcrumb-item active">New Wish</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <!-- left column -->
          <div class="col-md-12">
            <!-- general form elements -->
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">Wish Form</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form action="createwish.action" method = "post">
                <div class="card-body">
                  <div class="form-group col-sm-3">
                    <label for="category">Select Category</label>
                      <select class="form-control" id = "categoriesforwishes" name = "categoryID" required>
  						<option selected disabled>Select a Category</option>
                        </select>
                  </div>
                  <div class="form-group col-sm-3">
                    <label for="category">Income Source</label>
                      <select class="form-control" id = "categoriesforincome" name = "incomeSourceID" required>
  						<option selected disabled>Select source of income</option>
                        </select>
                  </div>
                  <div class="form-group col-sm-3">
                    <label for="cost">Amount</label>
                    <input type="number" step = ".01" class="form-control" id="amount" name = "amount" required>
                  </div>
                  
                  <div class="form-group col-sm-3">
                  <label>Date:</label>
                  <div class="input-group">
                    <div class="input-group date" id="datetimepicker1" data-target-input="nearest">
                    <input type="text" class="form-control datetimepicker-input" id="date" name = "date"/>
                    <div class="input-group-append" data-target="#datetimepicker1" data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                </div>
            </div>
                  <!-- /.input group -->
                </div>
                 <div class="form-group">
                    <label>Comment</label>
                     <textarea name = "comment" id = "description" class="form-control" rows="3" placeholder="Enter ..."></textarea>
                 </div>
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-submit">Submit</button>
                </div>
              </form>
            </div>
            <!-- /.card -->

</div>
</div>
<%@include file = "footer.jsp"%>