package erik121212.nl


import java.io.ByteArrayInputStream
import java.util

import org.dmg.pmml.{DataField, FieldName, PMML}
import org.jpmml.evaluator.{Evaluator, FieldValue, ModelEvaluatorFactory}
import org.jpmml.model.{ImportFilter, JAXBUtil}
import org.xml.sax.InputSource

object Main  { //extends App {

  def main(args: Array[String]): Unit = {
    //example1()
    //example2()
  }

  println("test2")

  def example1() {
//    var example =
//      <PMML xmlns="http://www.dmg.org/PMML-4_2" version="4.2">
//        <Header copyright="DMG.org"/>
//        <DataDictionary numberOfFields="3">
//          <DataField name="age" optype="continuous" dataType="double"/>
//          <DataField name="salary" optype="continuous" dataType="double"/>
//          <DataField name="number_of_claims" optype="continuous" dataType="double"/>
//        </DataDictionary>
//        <RegressionModel modelName="Sample for linear regression" functionName="regression" algorithmName="linearRegression" targetFieldName="number_of_claims">
//          <MiningSchema>
//            <MiningField name="age"/>
//            <MiningField name="salary"/>
//            <MiningField name="number_of_claims" usageType="target"/>
//          </MiningSchema>
//          <RegressionTable intercept="132.37">
//            <NumericPredictor name="age" exponent="1" coefficient="7.1"/>
//            <NumericPredictor name="salary" exponent="1" coefficient="0.01"/>
//          </RegressionTable>
//        </RegressionModel>
//      </PMML>

    val example2 =
      <PMML version="4.2" xmlns="http://www.dmg.org/PMML-4_2">
        <Header copyright="m06b181">
          <Application name="KNIME" version="3.1.2"/>
        </Header>
        <DataDictionary numberOfFields="8">
          <DataField name="score" optype="continuous" dataType="integer">
            <Interval closure="closedClosed" leftMargin="200.0" rightMargin="999.0"/>
          </DataField>
          <DataField name="PartyiDealTrxAmountLast4Hours" optype="continuous" dataType="double">
            <Interval closure="closedClosed" leftMargin="20.0" rightMargin="10000.0"/>
          </DataField>
          <DataField name="IPadressFirstUsedDaysAgo" optype="continuous" dataType="double">
            <Interval closure="closedClosed" leftMargin="0.1" rightMargin="15.0"/>
          </DataField>
          <DataField name="PartyId.Deviceid.FirstTrxDaysAgo" optype="continuous" dataType="double">
            <Interval closure="closedClosed" leftMargin="0.1" rightMargin="6.0"/>
          </DataField>
          <DataField name="RecentViewsTanMethod" optype="continuous" dataType="integer">
            <Interval closure="closedClosed" leftMargin="1.0" rightMargin="6.0"/>
          </DataField>
          <DataField name="iDealAfwijking" optype="continuous" dataType="double">
            <Interval closure="closedClosed" leftMargin="0.1" rightMargin="0.8"/>
          </DataField>
          <DataField name="AvgTimeOfIdealSessionLast2DaysTrust4" optype="continuous" dataType="integer">
            <Interval closure="closedClosed" leftMargin="10.0" rightMargin="400.0"/>
          </DataField>
          <DataField name="prediction" optype="continuous" dataType="boolean">
            <Value value="true"/>
            <Value value="false"/>
          </DataField>
        </DataDictionary>
        <RuleSetModel modelName="RuleSet" functionName="classification">
          <MiningSchema>
            <MiningField name="score" invalidValueTreatment="asIs"/>
            <MiningField name="PartyiDealTrxAmountLast4Hours" invalidValueTreatment="asIs"/>
            <MiningField name="IPadressFirstUsedDaysAgo" invalidValueTreatment="asIs"/>
            <MiningField name="PartyId.Deviceid.FirstTrxDaysAgo" invalidValueTreatment="asIs"/>
            <MiningField name="iDealAfwijking" invalidValueTreatment="asIs"/>
            <MiningField name="AvgTimeOfIdealSessionLast2DaysTrust4" invalidValueTreatment="asIs"/>
            <MiningField name="prediction" invalidValueTreatment="asIs" usageType="target"/>
          </MiningSchema>
          <RuleSet defaultConfidence="0.0">
            <RuleSelectionMethod criterion="firstHit"/>
            <SimpleRule score="true" weight="1.0">
              <CompoundPredicate booleanOperator="and">
                <SimplePredicate field="score" operator="greaterThan" value="799"/>
                <SimplePredicate field="PartyiDealTrxAmountLast4Hours" operator="greaterThan" value="189"/>
                <SimplePredicate field="IPadressFirstUsedDaysAgo" operator="lessThan" value="1"/>
                <SimplePredicate field="PartyId.Deviceid.FirstTrxDaysAgo" operator="lessThan" value="1"/>
                <SimplePredicate field="iDealAfwijking" operator="greaterThan" value="0.5"/>
                <SimplePredicate field="AvgTimeOfIdealSessionLast2DaysTrust4" operator="greaterThan" value="78"/>
              </CompoundPredicate>
            </SimpleRule>
            <SimpleRule score="false" weight="1.0">
              <True/>
            </SimpleRule>
          </RuleSet>
        </RuleSetModel>
      </PMML>

    val example =
        <PMML xmlns="http://www.dmg.org/PMML-4_2" version="4.2">
          <Header copyright="Copyright (c) 2013, DMG.org"/>
          <DataDictionary numberOfFields="6">
            <DataField name="age of individual" optype="continuous" dataType="double"/>
            <DataField name="gender" optype="categorical" dataType="string">
              <Value value="female"/>
              <Value value="male"/>
            </DataField>
            <DataField name="no of claims" optype="categorical" dataType="string">
              <Value value="0"/>
              <Value value="1"/>
              <Value value="2"/>
              <Value value="&gt;2"/>
            </DataField>
            <DataField name="domicile" optype="categorical" dataType="string">
              <Value value="suburban"/>
              <Value value="urban"/>
              <Value value="rural"/>
            </DataField>
            <DataField name="age of car" optype="continuous" dataType="double"/>
            <DataField name="amount of claims" optype="categorical" dataType="integer">
              <Value value="100"/>
              <Value value="500"/>
              <Value value="1000"/>
              <Value value="5000"/>
              <Value value="10000"/>
            </DataField>
          </DataDictionary>
          <NaiveBayesModel modelName="NaiveBayes Insurance" functionName="classification" threshold="0.001">
            <MiningSchema>
              <MiningField name="age of individual"/>
              <MiningField name="gender"/>
              <MiningField name="no of claims"/>
              <MiningField name="domicile"/>
              <MiningField name="age of car"/>
              <MiningField name="amount of claims" usageType="target"/>
            </MiningSchema>
            <BayesInputs>
              <BayesInput fieldName="age of individual">
                <TargetValueStats>
                  <TargetValueStat value="  100">
                    <GaussianDistribution mean="32.006" variance="0.352"/>
                  </TargetValueStat>
                  <TargetValueStat value="  500">
                    <GaussianDistribution mean="24.936" variance="0.516"/>
                  </TargetValueStat>
                  <TargetValueStat value=" 1000">
                    <GaussianDistribution mean="24.588" variance="0.635"/>
                  </TargetValueStat>
                  <TargetValueStat value=" 5000">
                    <GaussianDistribution mean="24.428" variance="0.379"/>
                  </TargetValueStat>
                  <TargetValueStat value="10000">
                    <GaussianDistribution mean="24.770" variance="0.314"/>
                  </TargetValueStat>
                </TargetValueStats>
              </BayesInput>
              <BayesInput fieldName="gender">
                <PairCounts value="male">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="4273"/>
                    <TargetValueCount value="500" count="1321"/>
                    <TargetValueCount value="1000" count="780"/>
                    <TargetValueCount value="5000" count="405"/>
                    <TargetValueCount value="10000" count="42"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="female">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="4325"/>
                    <TargetValueCount value="500" count="1212"/>
                    <TargetValueCount value="1000" count="742"/>
                    <TargetValueCount value="5000" count="292"/>
                    <TargetValueCount value="10000" count="48"/>
                  </TargetValueCounts>
                </PairCounts>
              </BayesInput>
              <BayesInput fieldName="no of claims">
                <PairCounts value="0">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="4698"/>
                    <TargetValueCount value="500" count="623"/>
                    <TargetValueCount value="1000" count="1259"/>
                    <TargetValueCount value="5000" count="550"/>
                    <TargetValueCount value="10000" count="40"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="1">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="3526"/>
                    <TargetValueCount value="500" count="1798"/>
                    <TargetValueCount value="1000" count="227"/>
                    <TargetValueCount value="5000" count="152"/>
                    <TargetValueCount value="10000" count="40"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="2">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="225"/>
                    <TargetValueCount value="500" count="10"/>
                    <TargetValueCount value="1000" count="9"/>
                    <TargetValueCount value="5000" count="0"/>
                    <TargetValueCount value="10000" count="10"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="&gt;2">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="112"/>
                    <TargetValueCount value="500" count="5"/>
                    <TargetValueCount value="1000" count="1"/>
                    <TargetValueCount value="5000" count="1"/>
                    <TargetValueCount value="10000" count="8"/>
                  </TargetValueCounts>
                </PairCounts>
              </BayesInput>
              <BayesInput fieldName="domicile">
                <PairCounts value="suburban">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="2536"/>
                    <TargetValueCount value="500" count="165"/>
                    <TargetValueCount value="1000" count="516"/>
                    <TargetValueCount value="5000" count="290"/>
                    <TargetValueCount value="10000" count="42"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="urban">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="1679"/>
                    <TargetValueCount value="500" count="792"/>
                    <TargetValueCount value="1000" count="511"/>
                    <TargetValueCount value="5000" count="259"/>
                    <TargetValueCount value="10000" count="30"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="rural">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="2512"/>
                    <TargetValueCount value="500" count="1013"/>
                    <TargetValueCount value="1000" count="442"/>
                    <TargetValueCount value="5000" count="137"/>
                    <TargetValueCount value="10000" count="21"/>
                  </TargetValueCounts>
                </PairCounts>
              </BayesInput>
              <BayesInput fieldName="age of car">
                <DerivedField optype="categorical" dataType="string">
                  <Discretize field="age of car">
                    <DiscretizeBin binValue="0">
                      <Interval closure="closedOpen" leftMargin="0" rightMargin="1"/>
                    </DiscretizeBin>
                    <DiscretizeBin binValue="1">
                      <Interval closure="closedOpen" leftMargin="1" rightMargin="5"/>
                    </DiscretizeBin>
                    <DiscretizeBin binValue="2">
                      <Interval closure="closedOpen" leftMargin="5"/>
                    </DiscretizeBin>
                  </Discretize>
                </DerivedField>
                <PairCounts value="0">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="927"/>
                    <TargetValueCount value="500" count="183"/>
                    <TargetValueCount value="1000" count="221"/>
                    <TargetValueCount value="5000" count="50"/>
                    <TargetValueCount value="10000" count="10"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="1">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="830"/>
                    <TargetValueCount value="500" count="182"/>
                    <TargetValueCount value="1000" count="51"/>
                    <TargetValueCount value="5000" count="26"/>
                    <TargetValueCount value="10000" count="6"/>
                  </TargetValueCounts>
                </PairCounts>
                <PairCounts value="2">
                  <TargetValueCounts>
                    <TargetValueCount value="100" count="6251"/>
                    <TargetValueCount value="500" count="1901"/>
                    <TargetValueCount value="1000" count="919"/>
                    <TargetValueCount value="5000" count="623"/>
                    <TargetValueCount value="10000" count="71"/>
                  </TargetValueCounts>
                </PairCounts>
              </BayesInput>
            </BayesInputs>
            <BayesOutput fieldName="amount of claims">
              <TargetValueCounts>
                <TargetValueCount value="100" count="8723"/>
                <TargetValueCount value="500" count="2557"/>
                <TargetValueCount value="1000" count="1530"/>
                <TargetValueCount value="5000" count="709"/>
                <TargetValueCount value="10000" count="100"/>
              </TargetValueCounts>
            </BayesOutput>
          </NaiveBayesModel>
        </PMML>


    // Version 1
    val is = new ByteArrayInputStream(example.toString.getBytes)
    val source = ImportFilter.apply(new InputSource(is))
    val model: PMML = JAXBUtil.unmarshalPMML(source)

    // Version 2
    val is2 = new ByteArrayInputStream(example2.toString.getBytes)
    val source2 = ImportFilter.apply(new InputSource(is2))
    val model2: PMML = JAXBUtil.unmarshalPMML(source2)

    val evaluator: Evaluator = ModelEvaluatorFactory.newInstance()
      .newModelManager(model).asInstanceOf[Evaluator]

    evaluator.verify()

    // Version 2
    val evaluator2: Evaluator = ModelEvaluatorFactory.newInstance()
      .newModelManager(model2).asInstanceOf[Evaluator]

    evaluator2.verify()


    val args2 = new util.LinkedHashMap[FieldName, FieldValue]()
    val fields2 = evaluator2.getActiveFields()

    val result2 = evaluator.evaluate(args2)
    // End Version 2


    val x: util.List[FieldName] = evaluator.getActiveFields
    val a: Array[AnyRef] = x.toArray

    // Version 2
    val x2: util.List[FieldName] = evaluator2.getActiveFields
    val a2: Array[AnyRef] = x2.toArray


    import scala.collection.JavaConversions._
    for(a <- x) {
      val x: DataField = evaluator.getDataField(a)
      x.toString
    }



    val reqFields = for {
      field <- evaluator.getActiveFields
      //v <- evaluator.getDataField(d)
    } yield field.getValue

    val l = reqFields.toList
    println(l)
    println(reqFields)

    //evaluator.getDataField
//    x.listIterator(f => evaluator.getDataField(f.asInstanceOf[ActiveField]))

//    for  {
//      activeField <- evaluator.getActiveFields()
//    } yield evaluator.getDataField(activeField)

//    x.forEach(f => evaluator.getDataField(f))

   val args = new util.LinkedHashMap[FieldName, FieldValue]()
   val fields = evaluator.getActiveFields()


    val score = new FieldName("score")
    val PartyiDealTrxAmountLast4Hours = new FieldName("PartyiDealTrxAmountLast4Hours")
    val IPadressFirstUsedDaysAgo = new FieldName("IPadressFirstUsedDaysAgo")
    val PartyIdDeviceidFirstTrxDaysAgo = new FieldName("PartyId.Deviceid.FirstTrxDaysAgo")
    val iDealAfwijking = new FieldName("iDealAfwijking")
    val AvgTimeOfIdealSessionLast2DaysTrust4 = new FieldName("AvgTimeOfIdealSessionLast2DaysTrust4")

    args2.put(score, evaluator2.prepare(score, "300"))
    args2.put(PartyiDealTrxAmountLast4Hours, evaluator2.prepare(PartyiDealTrxAmountLast4Hours, 10000))
    args2.put(IPadressFirstUsedDaysAgo, evaluator2.prepare(IPadressFirstUsedDaysAgo, "0.1"))
    args2.put(PartyIdDeviceidFirstTrxDaysAgo, evaluator2.prepare(PartyIdDeviceidFirstTrxDaysAgo, "0.1"))
    args2.put(iDealAfwijking, evaluator2.prepare(iDealAfwijking, "0.8"))
    args2.put(AvgTimeOfIdealSessionLast2DaysTrust4, evaluator2.prepare(AvgTimeOfIdealSessionLast2DaysTrust4, "400"))


    val result3 = evaluator2.evaluate(args2)
    println("Result3: " + result3)
    println("")

//    for(FieldName activeField : activeFields){
//      DataField dataField = evaluator.getDataField(activeField);


    //val age = new FieldName("age")
    //val salary = new FieldName("salary")

    val age_of_individual = new FieldName("age of individual")
    val gender = new FieldName("gender")
    val no_of_claims = new FieldName("no of claims")
    val domicile = new FieldName("domicile")
    val age_of_car = new FieldName("age of car")



    val start = System.currentTimeMillis()

    val total = 2 //

    for (i <- 10 to 50) {
//      args.put(age, evaluator.prepare(age, 10.32))
//      args.put(salary, evaluator.prepare(salary, 100.35))

      //args.put(age_of_individual, evaluator.prepare(age_of_individual, 42.5))
      args.put(age_of_individual, evaluator.prepare(age_of_individual, i*100))
      args.put(gender, evaluator.prepare(gender, "male"))

      args.put(no_of_claims, evaluator.prepare(no_of_claims, "2"))
      if (i% 2 == 0) {
        args.put(domicile, evaluator.prepare(domicile, "rural"))}
      else {
        args.put(domicile, evaluator.prepare(domicile, "urban"))
      }
      args.put(age_of_car, evaluator.prepare(age_of_car, i))

      //args.putAll(age_of_car,gender,no_of_claims,domicile,age_of_car)

      val result = evaluator.evaluate(args)
      println("Result: " + result)
    }

    val end = System.currentTimeMillis()

    val diff = end - start
    val avg = diff / total.toDouble
    val perSecond = (total / diff) * 1000

//    println(diff)
//    println(avg)
//    println(perSecond)
  }
example1()


  def example2() = {
    val example =
      <PMML xmlns="http://www.dmg.org/PMML-4_2" version="4.2">
        <Header copyright="DMG.org"/>
        <DataDictionary numberOfFields="5">
          <DataField name="gender" optype="categorical" dataType="string">
            <Value value="female"/>
            <Value value="male"/>
          </DataField>
          <DataField name="no of claims" optype="categorical" dataType="string">
            <Value value="0"/>
            <Value value="1"/>
            <Value value="3"/>
            <Value value="&amp;gt; 3"/>
            <Value value="2"/>
          </DataField>
          <DataField name="domicile" optype="categorical" dataType="string">
            <Value value="suburban"/>
            <Value value="urban"/>
            <Value value="rural"/>
          </DataField>
          <DataField name="age of car" optype="continuous" dataType="double"/>
          <DataField name="amount of claims" optype="continuous" dataType="double"/>
        </DataDictionary>
        <NeuralNetwork modelName="Neural Insurance" functionName="regression" activationFunction="logistic" numberOfLayers="2">
          <MiningSchema>
            <MiningField name="gender"/>
            <MiningField name="no of claims"/>
            <MiningField name="domicile"/>
            <MiningField name="age of car"/>
            <MiningField name="amount of claims" usageType="target"/>
          </MiningSchema>
          <NeuralInputs numberOfInputs="10">
            <NeuralInput id="0">
              <DerivedField optype="continuous" dataType="double">
                <NormContinuous field="age of car">
                  <LinearNorm orig="0.01" norm="0"/>
                  <LinearNorm orig="3.07897" norm="0.5"/>
                  <LinearNorm orig="11.44" norm="1"/>
                </NormContinuous>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="1">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="gender" value="male"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="2">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="no of claims" value="0"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="3">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="no of claims" value="1"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="4">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="no of claims" value="3"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="5">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="no of claims" value="&amp;gt; 3"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="6">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="no of claims" value="2"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="7">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="domicile" value="suburban"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="8">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="domicile" value="urban"/>
              </DerivedField>
            </NeuralInput>
            <NeuralInput id="9">
              <DerivedField optype="continuous" dataType="double">
                <NormDiscrete field="domicile" value="rural"/>
              </DerivedField>
            </NeuralInput>
          </NeuralInputs>
          <NeuralLayer numberOfNeurons="3">
            <Neuron id="10">
              <Con from="0" weight="-2.08148"/>
              <Con from="1" weight="3.69657"/>
              <Con from="2" weight="-1.89986"/>
              <Con from="3" weight="5.61779"/>
              <Con from="4" weight="0.427558"/>
              <Con from="5" weight="-1.25971"/>
              <Con from="6" weight="-6.55549"/>
              <Con from="7" weight="-4.62773"/>
              <Con from="8" weight="1.97525"/>
              <Con from="9" weight="-1.0962"/>
            </Neuron>
            <Neuron id="11">
              <Con from="0" weight="-0.698997"/>
              <Con from="1" weight="-3.54943"/>
              <Con from="2" weight="-3.29632"/>
              <Con from="3" weight="-1.20931"/>
              <Con from="4" weight="1.00497"/>
              <Con from="5" weight="0.033502"/>
              <Con from="6" weight="1.12016"/>
              <Con from="7" weight="0.523197"/>
              <Con from="8" weight="-2.96135"/>
              <Con from="9" weight="-0.398626"/>
            </Neuron>
            <Neuron id="12">
              <Con from="0" weight="0.904057"/>
              <Con from="1" weight="1.75084"/>
              <Con from="2" weight="2.51658"/>
              <Con from="3" weight="-0.151895"/>
              <Con from="4" weight="-2.88008"/>
              <Con from="5" weight="0.920063"/>
              <Con from="6" weight="-3.30742"/>
              <Con from="7" weight="-1.72251"/>
              <Con from="8" weight="-1.13156"/>
              <Con from="9" weight="-0.758563"/>
            </Neuron>
          </NeuralLayer>
          <NeuralLayer numberOfNeurons="1">
            <Neuron id="13">
              <Con from="10" weight="0.76617"/>
              <Con from="11" weight="-1.5065"/>
              <Con from="12" weight="0.999797"/>
            </Neuron>
          </NeuralLayer>
          <NeuralOutputs numberOfOutputs="1">
            <NeuralOutput outputNeuron="13">
              <DerivedField optype="continuous" dataType="double">
                <NormContinuous field="amount of claims">
                  <LinearNorm orig="0" norm="0.1"/>
                  <LinearNorm orig="1291.68" norm="0.5"/>
                  <LinearNorm orig="5327.26" norm="0.9"/>
                </NormContinuous>
              </DerivedField>
            </NeuralOutput>
          </NeuralOutputs>
        </NeuralNetwork>
      </PMML>

    val is = new ByteArrayInputStream(example.toString.getBytes)
    val source = ImportFilter.apply(new InputSource(is))
    val model: PMML = JAXBUtil.unmarshalPMML(source)

    val evaluator = ModelEvaluatorFactory.newInstance()
      .newModelManager(model).asInstanceOf[Evaluator]

    evaluator.verify()

    val args = new util.LinkedHashMap[FieldName, FieldValue]()
    val fields = evaluator.getActiveFields()

    val gender = new FieldName("gender")
    val nrClaims = new FieldName("no of claims")
    val domicile = new FieldName("domicile")
    val ageOfCar = new FieldName("age of car")

    val start = System.currentTimeMillis()

    val total = 1000000

    for (i <- 1 to total) {
      args.put(gender, evaluator.prepare(gender, "male"))
      args.put(nrClaims, evaluator.prepare(nrClaims, "3"))
      args.put(domicile, evaluator.prepare(domicile, "urban"))
      args.put(ageOfCar, evaluator.prepare(ageOfCar, 13.4))

      val result = evaluator.evaluate(args)
    }

    val end = System.currentTimeMillis()

    val diff = end - start
    val avg = diff / total.toDouble
    val perSecond = (total / diff) * 1000

    println(diff)
    println(avg)
    println(perSecond)
  }

}
