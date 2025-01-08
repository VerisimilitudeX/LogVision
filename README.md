<html>
<head>
  <meta charset="UTF-8" />
  <title>IlluminaLogVision README</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      line-height: 1.6;
      margin: 20px;
      max-width: 800px;
    }
    h1, h2, h3 {
      color: #1a1a1a;
    }
    code {
      background-color: #f5f5f5;
      padding: 2px 5px;
      border-radius: 3px;
    }
    pre {
      background-color: #f5f5f5;
      padding: 10px;
      border-radius: 5px;
      overflow: auto;
    }
    section {
      margin-bottom: 30px;
    }
    hr {
      margin-top: 40px;
      margin-bottom: 40px;
    }
  </style>
</head>

<body>
  <h1>IlluminaLogVision: Extended Epigenomic Analytics for NovaSeq 6000</h1>

  <section>
    <p>
      <strong>IlluminaLogVision</strong> is a Java-based toolkit dedicated to parsing and 
      interpreting Illumina NovaSeq 6000 log files. It incorporates detailed fields like 
      HPC node usage, system serials, Q30 statistics, pass-filter counts, and more. 
      Originally designed for epigenomic research pipelines, IlluminaLogVision 
      uses advanced metrics to aid in optimizing library preparation and HPC scheduling 
      while ensuring reliable data quality.
    </p>
  </section>

  <section>
    <h2>Scientific Rationale</h2>
    <ul>
      <li><strong>Comprehensive Error Analytics:</strong> By capturing and standardizing error-rate 
      measurements, we offer in-depth insight into basecalling quality, which is crucial 
      for sensitive epigenetic assays like WGBS (whole-genome bisulfite sequencing) or 
      histone ChIP-seq.</li>
      <li><strong>HPC Load Balancing:</strong> Multi-node HPC infrastructures often run 
      demultiplexing or alignment tasks in parallel. Tracking HPC node usage and run 
      distribution helps researchers identify bottlenecks and optimize resource allocation 
      across large-scale epigenomic projects.</li>
      <li><strong>Q30 and Pass-Filter Metrics:</strong> The proportion of bases above Q30 and 
      clusters passing filter are established indicators for run success. By aggregating 
      these measures per lane, researchers can more quickly refine library prep conditions 
      or revisit experimental design.</li>
      <li><strong>Yield and Cluster Density:</strong> Understanding how yield in gigabases 
      correlates with cluster density is essential for tuning loading concentrations, which 
      is especially beneficial for epigenetic workflows that rely on high coverage.
      </li>
    </ul>
  </section>

  <section>
    <h2>Core Features</h2>
    <ul>
      <li><em>Extended Parsing Logic:</em> Reads HPC node details, run folder paths, Q30 figures, 
      indexing barcodes, pipeline versions, and additional fields beyond basic logs.</li>
      <li><em>Rich Analytics:</em> Computes average error rate, error-rate standard deviation, 
      lane-specific HPC usage frequency, total yield, and more.</li>
      <li><em>Multi-File Compatibility:</em> Accepts varying log formats, from minimal 7-field lines 
      to larger lines featuring HPC node and pass-filter references.</li>
      <li><em>Epigenetic Application:</em> Integrates seamlessly into bioinformatics pipelines for 
      genomic and methylation assays, focusing on HPC usage and read quality in detail.</li>
    </ul>
  </section>

  <section>
    <h2>Usage</h2>
    <p>Clone or download this repository, then build and run either via Gradle or directly using 
    the Java command line. Ensure your logs are stored in <code>assets/</code>.</p>
    <pre>
gradle build
gradle run --args="real_runA.txt"
    </pre>
    <p>Alternatively, compile and run manually:</p>
    <pre>
javac *.java
java Main real_runA.txt
    </pre>
    <p>If no command-line argument is provided, the default file is <code>real_runA.txt</code>.</p>
  </section>

  <section>
    <h2>Extended Log Example</h2>
    <pre>
RUN-20250903 Lane1 HPC-Node4 SN3000123456 /seqdata/210801_M04281_0123_000000000-A1B2C 2025-09-03T09:10:22Z 42000000 0.0030 315 38.2 91.5 Q30=88.9 Index=ACTG NGS-v2.2.1 bcl2fastq2.20
    </pre>
    <p>
      Above, fields include Run ID, Lane, HPC Node, Machine Serial, Run Folder Path, Timestamp, 
      Read Count, Error Rate, Cluster Density, Yield (Gb), Pass-Filter Count, Q30, Index, 
      Pipeline Version, and Analysis Software.
    </p>
  </section>

  <hr />

  <section>
    <h2>Planned Research-Focused Updates</h2>
    <ul>
      <li><strong>Dynamic Epigenomic Reporting:</strong> Automated generation of QC charts for 
      methylation coverage vs. read error distribution, enabling real-time assessment of 
      CpG-specific data quality.</li>
      <li><strong>Integrative HPC Metrics:</strong> Collect HPC node performance stats (CPU load, 
      memory usage) to refine scheduling across batch-based or containerized workflows.</li>
      <li><strong>Hybrid Cloud Support:</strong> Real-time synchronization with off-site analysis 
      clusters for massive epigenome projects.</li>
    </ul>
  </section>

  <section>
    <h2>License and Contributing</h2>
    <p>IlluminaLogVision is released under the MIT License. We invite the research community to 
    contribute improvements or specialized fields for your local HPC environment, new epigenomic 
    metrics, or emergent QC needs. Together, we can continually refine how labs manage and interpret 
    vast sequencing data sets.</p>
  </section>

  <hr />

  <section>
    <h2>Release: IlluminaLogVision v3.1.0 – HPC & Epigenetics Advancement</h2>
    <p>
      We’re pleased to introduce <strong>IlluminaLogVision v3.1.0</strong>, enhancing HPC and 
      epigenetic synergy in Illumina NovaSeq logs. Our new pipeline examines deeper variance-based 
      metrics, ensuring more robust read quality for challenging epigenomic assays. 
      Key improvements include:
    </p>
    <ul>
      <li><strong>Unified HPC Monitoring:</strong> Track HPC node usage at scale, facilitating 
      real-time load balancing for emergent bisulfite or histone modification workflows.</li>
      <li><strong>Precision Q30 Integration:</strong> Increased fidelity in Q30 parsing, critical 
      for capturing subtle base-modification patterns in epigenomic libraries.</li>
      <li><strong>Advanced Variance and Standard Deviation Tools:</strong> Error-rate distributions 
      now reflect complexities in library composition and HPC concurrency, assisting in 
      troubleshooting or preempting run failures.</li>
      <li><strong>Streamlined Methylation QC:</strong> For labs verifying CpG coverage, the new 
      analytics output aids in consistent tracking of cluster density across multiple HPC nodes, 
      supporting precise comparisons across runs.</li>
    </ul>
    <p>
      We appreciate contributions from our research user base. This release underscores our ongoing 
      dedication to HPC-driven epigenomics, bridging the gap between raw sequencing logs and 
      deep biological inference.
    </p>
  </section>

</body>
</html>