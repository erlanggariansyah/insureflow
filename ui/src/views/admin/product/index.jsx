import React, { useCallback, useRef, useEffect, useState } from "react";
import ReactFlow, {
  addEdge,
  Background,
  Controls,
  MiniMap,
  useNodesState,
  useEdgesState,
  Handle,
  Position,
  ReactFlowProvider,
  useReactFlow
} from "reactflow";
import "reactflow/dist/style.css";
import {
  MdShield, MdGavel, MdPayments, MdTrendingUp,
  MdSave, MdAutoGraph, MdMoreVert
} from "react-icons/md";

import { productService } from "../../../services/productService";

const EntityNode = ({ id, data }) => {
  const { setNodes } = useReactFlow();

  const handleConfigChange = (key, newValue) => {
    setNodes((nds) =>
      nds.map((n) => {
        if (n.id === id) {
          const newConfigs = n.data.configs.map((c) =>
            c.key === key ? { ...c, value: newValue } : c
          );
          return { ...n, data: { ...n.data, configs: newConfigs } };
        }
        return n;
      })
    );
  };

  const handleNameChange = (e) => {
    setNodes((nds) =>
      nds.map((n) => {
        if (n.id === id) {
          return { ...n, data: { ...n.data, name: e.target.value } };
        }
        return n;
      })
    );
  };

  return (
    <div className="group min-w-[260px] rounded-2xl border border-gray-200 bg-white pb-2 shadow-xl transition-all hover:border-blue-500 dark:border-navy-700 dark:bg-navy-800">
      <div className={`flex items-center justify-between rounded-t-2xl px-4 py-3 text-white ${data.color}`}>
        <div className="flex items-center gap-2">
          {data.icon}
          <span className="text-xs font-black uppercase tracking-widest">{data.label}</span>
        </div>
        <div className="flex items-center gap-2">
          <span className="rounded-full bg-white/20 px-2 py-0.5 text-[9px] font-bold backdrop-blur-md">
            {data.status || 'ACTIVE'}
          </span>
        </div>
      </div>

      <div className="px-4 py-3">
        <input
          className="mb-3 w-full border-b border-dashed border-gray-300 bg-transparent text-sm font-bold text-navy-700 focus:border-blue-500 focus:outline-none dark:text-white"
          value={data.name || "Unnamed Entity"}
          onChange={handleNameChange}
        />
        <div className="space-y-2">
          {data.configs && data.configs.map((config, idx) => (
            <div key={idx} className="flex flex-col gap-1 border-b border-gray-50 pb-2 last:border-0 dark:border-navy-700">
              <div className="flex justify-between items-center text-[10px]">
                <span className="font-semibold text-gray-400 uppercase">{config.key}</span>
                {config.options ? (
                  <select
                    className="rounded bg-gray-50 px-1 py-0.5 font-mono text-blue-500 focus:outline-none dark:bg-navy-900"
                    value={config.value}
                    onChange={(e) => handleConfigChange(config.key, e.target.value)}
                  >
                    {config.options.map(opt => <option key={opt.id} value={opt.id}>{opt.name}</option>)}
                  </select>
                ) : (
                  <input
                    className="w-1/2 text-right font-mono text-blue-500 bg-transparent focus:outline-none border-b border-gray-200 dark:border-navy-600"
                    value={config.value || ""}
                    onChange={(e) => handleConfigChange(config.key, e.target.value)}
                  />
                )}
              </div>
            </div>
          ))}
        </div>
      </div>

      <Handle type="target" position={Position.Left} className="!h-3 !w-3 !bg-blue-500 border-2 border-white" />
      <Handle type="source" position={Position.Right} className="!h-3 !w-3 !bg-blue-500 border-2 border-white" />
    </div>
  );
};

const nodeTypes = { entity: EntityNode };

const ProductView = () => {
  const reactFlowWrapper = useRef(null);
  const { screenToFlowPosition } = useReactFlow();

  const [nodes, setNodes, onNodesChange] = useNodesState([]);
  const [edges, setEdges, onEdgesChange] = useEdgesState([]);
  const [loading, setLoading] = useState(true);

  // Save types for dropdown menus
  const [availableTypes, setAvailableTypes] = useState([]);

  // FETCH DATA FROM BACKEND WHEN COMPONENT MOUNTS
  useEffect(() => {
    const fetchGraphData = async () => {
      try {
        setLoading(true);
        // 1. Fetch Types
        const typesRes = await productService.getProductTypes();
        const types = typesRes?.data || [];
        setAvailableTypes(types);

        if (types.length === 0) {
          console.warn("No Product Types found in DB.");
          setLoading(false);
          return;
        }

        const firstType = types[0];

        // 2. Fetch Products
        const productsRes = await productService.getProductsByType(firstType.id);
        const products = productsRes?.data || [];

        let newNodes = [];
        let newEdges = [];
        let currentY = 50;

        for (let i = 0; i < products.length; i++) {
          const product = products[i];
          const prodNodeId = product.id;

          newNodes.push({
            id: prodNodeId,
            type: "entity",
            position: { x: 50, y: currentY },
            data: {
              type: "product",
              label: "Base Product",
              name: product.name,
              color: "bg-blue-700",
              icon: <MdShield />,
              status: product.isActive ? "ACTIVE" : "INACTIVE",
              configs: [
                { key: "Code", value: product.code },
                { key: "Product Type", value: product.productTypeId, options: types }
              ]
            }
          });

          // 3. Fetch Riders
          try {
            const ridersRes = await productService.getRidersByProduct(product.id);
            const riders = ridersRes?.data || [];

            riders.forEach((rider, idx) => {
              const riderNodeId = rider.id;
              const riderY = currentY + (idx * 200);

              newNodes.push({
                id: riderNodeId,
                type: "entity",
                position: { x: 450, y: riderY },
                data: {
                  type: "rider",
                  label: "Rider",
                  name: rider.name,
                  color: "bg-purple-600",
                  icon: <MdPayments />,
                  status: rider.isActive ? "ACTIVE" : "INACTIVE",
                  configs: [
                    { key: "Code", value: rider.code }
                  ]
                }
              });

              newEdges.push({
                id: `e-${prodNodeId}-${riderNodeId}`,
                source: prodNodeId,
                target: riderNodeId,
                animated: true,
                label: "includes"
              });
            });
          } catch (err) {
            console.error(`Failed to fetch riders for product ${product.id}`, err);
          }

          currentY += 400; // spacing
        }

        setNodes(newNodes);
        setEdges(newEdges);

      } catch (error) {
        console.error("Failed to load initial graph data from Database:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchGraphData();
  }, [setNodes, setEdges]);

  const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), [setEdges]);

  const onDragStart = (event, nodeType) => {
    event.dataTransfer.setData("application/reactflow", nodeType);
    event.dataTransfer.effectAllowed = "move";
  };

  const onDragOver = useCallback((event) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = "move";
  }, []);

  const onDrop = useCallback(
    (event) => {
      event.preventDefault();
      const type = event.dataTransfer.getData("application/reactflow");
      if (!type) return;

      const position = screenToFlowPosition({ x: event.clientX, y: event.clientY });

      let nodeData = { type: type, configs: [] };
      switch (type) {
        case "product":
          nodeData = {
            ...nodeData, label: "Product", name: "New Product", color: "bg-blue-700", icon: <MdShield />,
            configs: [
              { key: "Code", value: "PRD-" + Math.floor(Math.random() * 1000) },
              { key: "Product Type", value: availableTypes.length ? availableTypes[0].id : "", options: availableTypes }
            ]
          };
          break;
        case "rider":
          nodeData = {
            ...nodeData, label: "Rider", name: "New Rider", color: "bg-purple-600", icon: <MdPayments />,
            configs: [
              { key: "Code", value: "RDR-" + Math.floor(Math.random() * 1000) }
            ]
          };
          break;
        case "rule":
          nodeData = {
            ...nodeData, label: "Rule", name: "Eligibility Rule", color: "bg-orange-500", icon: <MdGavel />,
            configs: [
              { key: "Expression", value: "age >= 18" },
              { key: "Message", value: "Must be 18+" }
            ]
          };
          break;
        case "rate": nodeData = { ...nodeData, label: "Pricing", name: "New Rate", color: "bg-emerald-600", icon: <MdTrendingUp />, configs: [] }; break;
        default: break;
      }

      const newNode = {
        id: crypto.randomUUID(), // Always use UUID
        type: "entity",
        position,
        data: nodeData,
      };

      setNodes((nds) => nds.concat(newNode));
    },
    [screenToFlowPosition, setNodes, availableTypes]
  );

  const onAutoLayout = useCallback(() => {
    setNodes((nds) => nds.map((node, i) => ({
      ...node, position: { x: (node.data.type === 'product' ? 50 : 450), y: Math.floor(i / 2) * 300 + 50 }
    })));
  }, [setNodes]);

  const onSaveConfig = useCallback(async () => {
    try {
      setLoading(true);

      // 1. Identify Product Node
      const productNode = nodes.find(n => n.data.type === "product");
      if (!productNode) {
        alert("Harap tambahkan setidaknya 1 Product (Base) ke kanvas.");
        setLoading(false);
        return;
      }

      // Extract configs helper
      const getConfig = (configs, key) => configs?.find(c => c.key === key)?.value;

      // 2. Build Base Product DTO
      const productDto = {
        id: productNode.id,
        code: getConfig(productNode.data.configs, "Code"),
        name: productNode.data.name,
        productTypeId: getConfig(productNode.data.configs, "Product Type")
      };

      // 3. Build Riders DTO via edges
      const ridersDtos = [];
      const rulesDtos = [];

      // Find all edges starting from product to a rider
      const productEdges = edges.filter(e => e.source === productNode.id);

      nodes.forEach(node => {
        if (node.data.type === "rider") {
          // Check if this rider is connected to the product via incoming edge (source = product.id, target = rider.id)
          const isConnectedToProduct = productEdges.some(e => e.target === node.id);

          if (isConnectedToProduct) {
            ridersDtos.push({
              id: node.id,
              code: getConfig(node.data.configs, "Code"),
              name: node.data.name,
              isMandatory: false // Default implementation
            });
          }
        }

        if (node.data.type === "rule") {
          // Check what this rule is connected to
          const connectedEdge = edges.find(e => e.target === node.id);
          if (connectedEdge) {
            rulesDtos.push({
              id: node.id,
              subId: connectedEdge.source, // Resolves Question 2 correctly: references Product or Rider
              name: node.data.name,
              expression: getConfig(node.data.configs, "Expression"),
              message: getConfig(node.data.configs, "Message")
            });
          }
        }
      });

      const payload = {
        product: productDto,
        riders: ridersDtos,
        rules: rulesDtos
      };

      console.log("Saving Backend Sync Payload:", payload);

      // CALL POST /api/v1/products/sync
      await productService.syncProductConfig(payload);

      alert("Konfigurasi Kanvas Berhasil Disimpan & Tersikronisasi ke Database!");
    } catch (err) {
      console.error(err);
      alert("Gagal menyimpan konfigurasi: " + err.message);
    } finally {
      setLoading(false);
    }
  }, [nodes, edges]);

  const draggableItems = [
    { type: "product", label: "Base Product", desc: "Core policy configuration", icon: <MdShield className="h-5 w-5" />, color: "text-blue-500 bg-blue-50 dark:text-blue-400 dark:bg-blue-900/30", borderColor: "hover:border-blue-500" },
    { type: "rider", label: "Add Rider", desc: "Supplementary coverage", icon: <MdPayments className="h-5 w-5" />, color: "text-purple-500 bg-purple-50 dark:text-purple-400 dark:bg-purple-900/30", borderColor: "hover:border-purple-500" },
    { type: "rule", label: "Eligibility Rule", desc: "Validation mechanisms", icon: <MdGavel className="h-5 w-5" />, color: "text-orange-500 bg-orange-50 dark:text-orange-400 dark:bg-orange-900/30", borderColor: "hover:border-orange-500" },
    { type: "rate", label: "Pricing Table", desc: "Premium rate metrics", icon: <MdTrendingUp className="h-5 w-5" />, color: "text-emerald-500 bg-emerald-50 dark:text-emerald-400 dark:bg-emerald-900/30", borderColor: "hover:border-emerald-500" }
  ];

  return (
    <div className="flex h-[calc(100vh-100px)] w-full flex-col gap-4 mt-4">
      {loading && (
        <div className="absolute inset-0 z-50 flex items-center justify-center bg-white/50 backdrop-blur-sm dark:bg-navy-900/50">
          <span className="text-sm font-bold text-blue-500">Syncing Operation...</span>
        </div>
      )}
      <div className="flex flex-grow gap-5 overflow-hidden">

        <div className="flex w-[280px] flex-col rounded-2xl bg-gradient-to-b from-white to-gray-50 border border-gray-100 p-5 shadow-xl shadow-gray-200/50 dark:border-navy-700 dark:from-navy-800 dark:to-navy-900 dark:shadow-navy-900">
          <div className="mb-6 flex flex-col">
            <h2 className="text-lg font-bold text-navy-700 dark:text-white">Workspace</h2>
            <p className="text-xs text-gray-500 dark:text-gray-400">Drag components to canvas</p>
          </div>
          
          <div className="flex-1 space-y-3 overflow-y-auto pr-1 custom-scrollbar">
            {draggableItems.map((item, i) => (
              <div
                key={i}
                draggable
                onDragStart={(e) => onDragStart(e, item.type)}
                className={`group flex cursor-move items-start gap-3 rounded-xl border border-transparent bg-white p-3 shadow-sm transition-all hover:-translate-y-0.5 hover:shadow-md ${item.borderColor} dark:bg-navy-800 dark:hover:bg-navy-800/80`}
              >
                <div className={`mt-0.5 flex items-center justify-center rounded-lg p-2 ${item.color} transition-transform group-hover:scale-110`}>
                  {item.icon}
                </div>
                <div className="flex flex-col">
                  <span className="text-sm font-bold text-navy-700 dark:text-white">{item.label}</span>
                  <span className="text-[10px] font-medium text-gray-400">{item.desc}</span>
                </div>
              </div>
            ))}
          </div>

          <div className="mt-4 flex flex-col gap-3 border-t border-gray-200 pt-5 dark:border-navy-700">
            <button 
              onClick={onAutoLayout} 
              className="flex w-full items-center justify-center gap-2 rounded-xl border border-gray-200 bg-white p-2.5 text-sm font-bold text-navy-700 transition hover:bg-gray-50 dark:border-navy-600 dark:bg-navy-800 dark:text-white dark:hover:bg-navy-700"
            >
              <MdAutoGraph className="h-5 w-5 text-gray-500" />
              Auto-Align Canvas
            </button>
            <button 
              onClick={onSaveConfig} 
              className="flex w-full items-center justify-center gap-2 rounded-xl bg-gradient-to-r from-blue-600 to-blue-400 p-3 text-sm font-bold text-white shadow-lg shadow-blue-500/30 transition-all hover:scale-[1.02] hover:shadow-blue-500/50 active:scale-95"
            >
              <MdSave className="h-5 w-5" />
              Sync Database
            </button>
          </div>
        </div>

        <div
          className="flex-grow overflow-hidden rounded-2xl border border-gray-100 bg-gray-50 dark:border-transparent dark:bg-navy-900 shadow-inner"
          ref={reactFlowWrapper}
          onDrop={onDrop}
          onDragOver={onDragOver}
        >
          <ReactFlow
            nodes={nodes}
            edges={edges}
            onNodesChange={onNodesChange}
            onEdgesChange={onEdgesChange}
            onConnect={onConnect}
            nodeTypes={nodeTypes}
            fitView
            deleteKeyCode={["Backspace", "Delete"]}
          >
            <Background variant="dots" gap={20} size={1} color="#cbd5e0" />
            <Controls className="!bg-white !shadow-xl !border-0 dark:!bg-navy-800" />
            <MiniMap className="!bg-white dark:!bg-navy-800 rounded-lg" />
          </ReactFlow>
        </div>
      </div>
    </div>
  );
};

export default function WrappedProductView() {
  return (
    <ReactFlowProvider>
      <ProductView />
    </ReactFlowProvider>
  );
}