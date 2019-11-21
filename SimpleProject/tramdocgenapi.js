var JSZip = require('jszip');
var Docxtemplater = require('docxtemplater');
var ImageModule = require('docxtemplater-image-module')
var https = require('https')
var fs = require('fs');
var path = require('path');
var program_mongoose = mongoose.model('Program')
var modelfield_mongoose = mongoose.model('modelfield')
let Grid = require("gridfs-stream");
eval(`Grid.prototype.findOne = ${Grid.prototype.findOne.toString().replace('nextObject', 'next')}`);
let conn = mongoose.connection;
Grid.mongo = mongoose.mongo;
let gfs;

const Rundesign = require('../models/RunDesign');
const Suiteview = require('../models/suiteview');
 var rundsg_mongoose = mongoose.model('rundesign')


module.exports = (router, io) => {
    router.post('/tramdocgen',async (req, res, next) => {
        console.log(req.body, "TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT")

        function base64DataURLToArrayBuffer(dataURL) {
            const base64Regex = /^data:image\/(png|jpg);base64,/;
            if (!base64Regex.test(dataURL)) {
                return false;
            }
            const stringBase64 = dataURL.replace(base64Regex, "");
            let binaryString;
            if (typeof window !== "undefined") {
                binaryString = window.atob(stringBase64);
            } else {
                binaryString = new Buffer(stringBase64, "base64").toString("binary");
            }
            const len = binaryString.length;
            const bytes = new Uint8Array(len);
            for (let i = 0; i < len; i++) {
                const ascii = binaryString.charCodeAt(i);
                bytes[i] = ascii;
            }
            return bytes.buffer;
        }



        const imageOpts = {
            getImage(tag) {
                return base64DataURLToArrayBuffer(tag);
            },
            getSize() {
                return datafromclient.length>0 ? datafromclient[0].mode == 'AQuA' ? [650, 350] : [350,650] : [650, 350];
            },
        };


        var finaldocarr = []


        var datafromclient = req.body.exportdata
           // console.log(datafromclient,"dfromc")

        datafromclient.forEach((val, key) => {
            //console.log(val)
            finaldocarr.push(val)
        })

        

       // console.log(finaldocarr,"!!!!!!!!!!!!!!!!!!!")
        var templatename;
        var resdocname;

        var gendata = {}
        gendata['final'] = finaldocarr
            //console.log(JSON.stringify(gendata))
        console.log("start")
         await gendata.final.map(async (val, key) => {
            
                //console.log(val)
                if (val.type == "tts") {
                    templatename = "testsuite"
                    resdocname = "testsuite_generated"

                    val.bots.forEach(function(val1, key1) {

                        console.log(val1)
                        val1.tcdata.forEach(function(val2, key2) {
                            console.log(val2)
                            console.log(val1.btindex)
                            val2.application = val2.design[0].testcase.application
                            val2.automatable = val2.design[0].testcase.automatable
                            val2.brdreference = val2.design[0].testcase.brdreference
                            val2.description = val2.design[0].testcase.description
                            val2.testcasename = val2.design[0].name
                            val2.currtcsteps = val2.design[0].steps[val1.btindex]
                        })

                    })
                    if(gendata.final.length-1 == key){
                        await writefunc();
                    }
                } /*testinstance*/
                else if (val.type == "ebt" || val.type == "ebtc" || val.type == "ebtf" || val.type == "ebte") {
                    templatename = "bot"
                    resdocname = "bot_generated"

                    val.tcdata.forEach(function(val1, key1) {
                        console.log(val1)
                        console.log(val.btindex)
                        val1.application = val1.design[0].testcase.application
                        val1.automatable = val1.design[0].testcase.automatable
                        val1.brdreference = val1.design[0].testcase.brdreference
                        val1.description = val1.design[0].testcase.description
                        val1.testcasename = val1.design[0].name
                        val1.currtcsteps = val1.design[0].steps[val.btindex]
                    })
                    if(gendata.final.length-1 == key){
                        await writefunc();
                    }
                } else if (val.type == "exebot") {

                    templatename = "exebot"
                    console.log()
                    var tc_dat=await rundsg_mongoose.find({testrun_id: val.testsuitid}).sort('case_order')
                        //console.log(data,"75")
                    console.log(tc_dat,"118")
                    var stepsarr = []
                    var d2=await tc_dat.map(async (x, xk) => {
                    //console.log(x,x.des)
                        
                        var ty=[];
                        if(x.design[0].steps[val.bot_ind-1]){
                            if(x.design_id!=null){
                                console.log(x.tc_name.testcase.case_name)
                            var ty = x.design[0].steps[val.bot_ind-1].map(y => {var z=[].concat.apply([], y).map(xx=>{xx.name=x.tc_name.testcase.case_name;return xx}); return z})
                            }else{
                                var ty = x.design[0].steps[val.bot_ind-1].map(y => [].concat.apply([], y))
                            }
                            
                        }
                        
                        var fsteps = [].concat.apply([], ty)                        
                        stepsarr = stepsarr.concat(fsteps)  
                       if(tc_dat.length -1 == xk){
                        console.log(stepsarr.length,"length")
                       }    
                        /* fsteps.forEach((stval, stkey) => {    
                            stepsarr.push(stval)
                            if(fsteps.length-1 == stkey){
                               
                            }
                        }) */

                    })
                    //for(var i=0;i<stepsarr.length;i++){
                   gendata.final[key].stpdata=stepsarr
                   var one=await getScreentshot(stepsarr,key)                  

                    
                    //console.log("len",tot_step.length)
                    

                } else {

                    templatename = "testcase"
                    resdocname = "testcase_generated"

                    val.tcdata.forEach(function(val1, key1) {
                        console.log(val1)
                        console.log(val.btindex)
                        val1.application = val1.design[0].testcase.application
                        val1.automatable = val1.design[0].testcase.automatable
                        val1.brdreference = val1.design[0].testcase.brdreference
                        val1.description = val1.design[0].testcase.description
                        val1.testcasename = val1.design[0].name
                        val1.currtcsteps = val1.design[0].steps[val.btindex]
                    })

                    if(gendata.final.length-1 == key){
                        await writefunc();
                    }

                }

                


            })
            //console.log(JSON.stringify(gendata))

        console.log("end")

        async function checkstepslen(len,curr_len,key,img,genkey){
            //console.log(len,curr_len)
            if(img){
               // console.log("189")
                gendata.final[genkey].stpdata[key].screenshotdup=img;
            }
            if(len == curr_len){
                //gendata.final[genkey].stpdata=stepsarr
                await writefunc()
            }
        }

        async function writefunc(){
            console.log(gendata)
            var imageModule = new ImageModule(imageOpts);

            var content = fs
                .readFileSync(path.resolve(__dirname, './doctemplate/' + templatename + '.docx'), 'binary');
            //looplist
            var zip = new JSZip(content);
            var docx = new Docxtemplater()  
    
            docx.attachModule(imageModule)
            docx.setData(gendata)
            docx.loadZip(zip)
            docx.render();
     
    
            var buffer = docx.getZip().generate({ type: "nodebuffer" });
    
            var resultpath = './routes/doctemplate/' + resdocname + '.docx'
    
            /*fs.writeFile(resultpath,buffer).then(function(){
    
            res.download(resultpath, 'new.docx', function(err){
                if (err) {
                  // if the file download fails, we throw an error
                  throw err;
                }else{
                    console.log("down-load")
                }
            })
            */
    
            fs.writeFile(resultpath, buffer, (err) => {
                if (err) {
                    throw err;
                } else {
                    console.log(resultpath,'The file has been saved!')
                    res.download(resultpath, 'new.docx', function(err) {
                        if (err) {
                            // if the file download fails, we throw an error
                            throw err;
                        } else {
                            console.log("down-load")
                        }
                    })
                }
            });
        }


      
    async function getScreentshot(steps,genkey){        
       
        //var screenshot_data = Buffer.from(id, 'base64')
    return new Promise(async function(resolve ,reject){ 
                gfs = Grid(conn.db); 
                var count=0;               
                steps.map(async (res ,key)=>{
                  //  console.log("one")
                if(res.screenshot != 'x' && res.screenshot != undefined){            
                    
                console.log(res.screenshot,"enter int o dms")               
                //var md5_check = crypto.createHash('md5').update(screenshot_data).digest("hex");             
                var  streamid=res.screenshot
                await  gfs.findOne({ _id: streamid },async function (err, file) { 
                // console.log(file,'160 dms------------------------')
                    if (err) {
                    count++;
                    checkstepslen(steps.length, count,key,null,genkey)
                    } else if (!file) {
                        count++;
                        checkstepslen(steps.length, count,key,null,genkey)
                    }

                    var imag;
                    if(file){                
                    let data = [];                    
                    var readstream = gfs.createReadStream({ _id: streamid });

                    await readstream.on('data', (chunk) => {  data.push(chunk);   });

                    await readstream.on('end', () => {
                        data = Buffer.concat(data);             
                        //console.log(file.contentType);                
                                if (  file.contentType.indexOf("image") > -1  ) {                       
                                //console.log("file.contentType")                         
                                imag = 'data:image/png;base64,' + Buffer(data).toString('base64')//.toString('utf-8')
                                
                                count++
                                checkstepslen(steps.length, count,key,imag,genkey)
                                } else {                            
                                imag =  Buffer(data)
                                console.log(imag,"--------------")                           
                                count++
                                checkstepslen(steps.length, count,key,imag,genkey)

                    }               
                    });

                    await readstream.on('error', (err) => {
                        //console.log('An error occurred!', err);
                        count++
                        checkstepslen(steps.length, count,key,null,genkey)
                    });                    
                        
                }
                
                });
                              
             }else { count++; checkstepslen(steps.length, count,key,null,genkey) }
            
             } )

             
            }) 
        

        
      

        }

    })



    /*fs.readFile(resultpath, function (err, data) {
        if (err) throw err;

        var resobj={"filename":resdocname+'.docx',"docdata":data}
        //console.log(data)
        
        //const docPath = path.join(__dirname, './doctemplate/tr.docx');

        //res.send(resobj)

    });
    */




    //res.send("downloaded successfully")
    //})

    return router;
}