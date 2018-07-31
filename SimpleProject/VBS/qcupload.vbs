
Set objArgs = WScript.Arguments
argX = objArgs.Item(0)
'argX = "Yes##http://192.168.22.222:8080/qcbin##bankinguser01##banking123##DEFAULT##Automation_Initiatives_01##Cyclos_Demo_1##Launch Cyclos and Perform a Payment to system##150##E:\Anuj\git-repo\bigbee.testng.framework\SimpleProject\Logs\data.csv##Filename##Finalstatus"
qcUpload (argX)


Function qcUpload(ByVal argValue)
    splitArgValue = Split(argValue, "##")
    qcExecuteCondition = True
    For ix = 1 To UBound(splitArgValue) - 1
        If Trim(LCase(splitArgValue(ix))) = "{null}" Then
            qcExecuteCondition = False
            Exit For
        End If
    Next
    If qcExecuteCondition Then
        edQCIntegrated = splitArgValue(0)
        edQCServerURL = splitArgValue(1)
        edQCUser = splitArgValue(2)
        edQCPassword = splitArgValue(3)
        edQCDomain = splitArgValue(4)
        edQCProject = splitArgValue(5)
        bdTestSetFolderName = splitArgValue(6)
        bdTestSetName = splitArgValue(7)
        tscQcTestId = splitArgValue(8)
        Set QCConnection = CreateObject("TDApiOle80.TDConnection")
        QCConnection.InitConnectionEx edQCServerURL
        QCConnection.login edQCUser, edQCPassword
        QCConnection.Connect edQCDomain, edQCProject
        'exit function
        Set tsTreeMgr = QCConnection.testsettreemanager
        Set tsFolder = tsTreeMgr.NodeByPath("Root\" & bdTestSetFolderName)
        ' --Search for the test set passed as an argument to the example code
        Set tsList = tsFolder.FindTestSets(bdTestSetName)

        '------Accessing the Test Cases inside the Test SEt ----

        Set theTestSet = tsList.Item(1)
        For Each testsetfound In tsList
            'msgbox "test set found"
            Set tsFolder = testsetfound.TestSetFolder
            Set tsTestFactory = testsetfound.tsTestFactory
            Set testSetFilter = tsTestFactory.Filter
            testSetFilter.Filter("TC_TEST_ID") = tscQcTestId
            Set tsTestList = testSetFilter.NewList()
            For Each tsTest In tsTestList
                'MsgBox (tsTest.Name+","+ tsTest.ID)
                testrunname = "Test Case name from excel sheet"
                '----Accesssing the Run Factory ---
                Set RunFactory = tsTest.RunFactory
                Set obj_theRun = RunFactory.AddItem(CStr(testrunname))
                obj_theRun.CopyDesignSteps
                obj_theRun.Post
                Set runStepF = obj_theRun.StepFactory
                'Set lst = runStepF.NewList("")
                'set listV=lst.Fields
                'Set Fso = CreateObject("Scripting.FilesystemObject")
                'Set f = Fso.createtextfile("C:\Prem\Softwares\Exelerate\fieldList.txt", True, False)
                'for each Field in listV
            '       f.writeline field
        '       next
                Set Fso = CreateObject("Scripting.FilesystemObject")
                Set f = Fso.OpenTextFile(splitArgValue(9))
                i = 0
                Do While Not f.AtEndOfStream
                    fdata = f.ReadLine
                    arrdata = Split(fdata, ",")
                    Set lstFilter = runStepF.Filter
                    lstFilter.Filter("ST_STEP_NAME") = CStr(arrdata(0))
                    Set lst = lstFilter.NewList()
                    For Each Item In lst
                        Set runStep2 = Item
                        runStep2.Status = arrdata(1)
                        runStep2.Field("ST_ACTUAL") = Replace(Replace(arrdata(2), "{VBNewline}", vbNewLine), "{comma}", ",")
                        runStep2.Post
                    Next
                Loop
                f.Close
                Set Fso = Nothing
                Set CurrentRunObj = obj_theRun.Attachments
                Set AttcObj = CurrentRunObj.AddItem(Null)
                AttcObj.Filename = CStr(splitArgValue(10))
                AttcObj.Type = 1
                AttcObj.Post
                AttcObj.Refresh
                obj_theRun.Status = CStr(splitArgValue(11))
                obj_theRun.Post
            Next
        Next
        QCConnection.logout
    End If
End Function
